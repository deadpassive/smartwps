package uk.ac.glam.smartwps.base.server;

import java.io.File;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.logging.Logger;

import uk.ac.glam.smartwps.base.shared.RESTConnectionException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import java.util.logging.Level;

/**
 * Provides useful methods for accessing the GeoServer REST interface.
 * 
 * @author Jon Britton
 */
public class GeoServerREST {

	private static Client client = Client.create();
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");
	private String geoserverURL;
	private String username;
	private String password;
	
	/**
	 * TODO: document
	 * @param geoserverURL
	 * @param username
	 * @param password
	 */
	public GeoServerREST(String geoserverURL, String username, final String password) {
		this.geoserverURL = geoserverURL;
		this.username = username;
		this.password = password;
		setAuthentication();
	}

	/**
	 * Create a new workspace.
	 * 
	 * @param workspace
	 *            The name of the workspace to create
	 * @throws RESTConnectionException 
	 */
	public void createWorkspace(String workspace) throws RESTConnectionException {
		WebResource r = client.resource(geoserverURL + "/rest/workspaces/"
				+ workspace);
		LOGGER.log(Level.INFO, "Creating workspace at {0}", r.getURI());
		
		try {
			LOGGER.log(Level.INFO, "Checking if workspace ''{0}'' exists", workspace);
			r.accept("application/xml").get(String.class);
			LOGGER.info("Workspace already exists");
		} catch (UniformInterfaceException ue) {
			if (ue.getResponse().getStatus() == 404) {
				LOGGER.log(Level.INFO, "Workspace ''{0}'' doesn''t exist, creating...", workspace);
				WebResource workspaces = client.resource(geoserverURL
						+ "/rest/workspaces");
				workspaces.type("application/xml")
						.post(String.class, "<workspace><name>" + workspace	+ "</name></workspace>");
			}
		} catch (ClientHandlerException e) {
			throw new RESTConnectionException("Failed to connect to GeoServer REST: " + e.getMessage());
		}
	}

	/**
	 * Create a new Coverage and CoverageStore for the given file. This method
	 * does not copy the file to the geoserver data directory, instead GeoServer
	 * accesses the file from its current location.
	 * 
	 * All coverage types supported by GeoServer should be supported by this
	 * method, although it has only been tested with GeoTiff data.
	 * 
	 * @param file - The coverage file
	 * @param workspace - The name of the workspace
	 * @param coverageStoreName - The name of the new coveragestore
	 * @param coverageName - The name of the new coverage
	 */
	public void linkToExternalCoverage(File file, String workspace, String coverageStoreName, 
			String coverageName) {
		WebResource r = client.resource(geoserverURL + "/rest/workspaces/"
				+ workspace + "/coveragestores");
		try {
			// POST coveragestore
			String xml = "<coverageStore><name>" + coverageStoreName
					+ "</name><workspace>" + workspace
					+ "</workspace><enabled>true</enabled></coverageStore>";
			r.type("application/xml").post(String.class, xml);

			// PUT coverage
			r = client.resource(geoserverURL + "/rest/workspaces/" + workspace
					+ "/coveragestores/" + coverageName
					+ "/external.geotiff?configure=first&coverageName="
					+ coverageName);
			r.put(String.class, "file:/" + file.getAbsolutePath());
		} catch (UniformInterfaceException e) {
            LOGGER.log(Level.SEVERE, "REST error", e);
		}
	}
	
	/**
	 * Create a new Coverage and CoverageStore for the given file. The file is uploaded to the server
	 * 
	 * All coverage types supported by GeoServer should be supported by this
	 * method, although it has only been tested with GeoTiff data.
	 * 
	 * @param file - The coverage file
	 * @param workspace - The name of the workspace
	 * @param coverageStoreName - The name of the new coveragestore
	 * @param coverageName - The name of the new coverage
	 */
	public void uploadCoverage(File file, String workspace, String coverageStoreName, 
			String coverageName) {
		WebResource r = client.resource(geoserverURL + "/rest/workspaces/"
				+ workspace + "/coveragestores");
		try {
			// POST coveragestore
			LOGGER.info("POSTing coveragestore");
			String xml = "<coverageStore><name>" + coverageStoreName
					+ "</name><workspace>" + workspace
					+ "</workspace><enabled>true</enabled></coverageStore>";
			r.type("application/xml").post(String.class, xml);

			// PUT coverage
			LOGGER.info("PUTting coverage");
			r = client.resource(geoserverURL + "/rest/workspaces/" + workspace
					+ "/coveragestores/" + coverageName
					+ "/file.geotiff?configure=first&coverageName="
					+ coverageName);
			r.type("image/tiff").put(File.class, file);
		} catch (UniformInterfaceException e) {
			LOGGER.log(Level.SEVERE, "REST error", e);
		}
	}

	/**
	 * Get's the layers details in XML format.
	 * 
	 * @param layerName - The name of the layer
	 * @return the layer xml
	 */
	public String getLayerXML(String layerName) {
		// GET layers XML
		WebResource r = client.resource(geoserverURL + "/rest/layers/"
				+ layerName + ".xml");
		String layerXML = r.get(String.class);
		return layerXML;
	}
	
	/**
	 * Get's the xml for all styles on the server
	 * 
     * @return The styles xml
	 */
	public String getStylesXML() {
		// GET layers XML
		WebResource r = client.resource(geoserverURL + "/rest/styles.xml");
		String stylesXML = r.get(String.class);
		return stylesXML;
	}
	
	/**
	 * TODO: document
	 * @param layerName
	 */
	public void setAllStylesForLayer(String layerName) {
		// Get style XML
		String styleXML = getStylesXML();
		String xml = "<layer>"+styleXML+"</layer>";
		WebResource r = client.resource(geoserverURL + "/rest/layers/" + layerName + ".xml");
		r.type("application/xml").put(String.class, xml);
	}
	
	/**
	 * Sets the layers default style. If it already has a default style, this
	 * will be replaced. The style must already exist on the server.
	 * 
	 * @param layerName
	 *            The name of the layer
	 * @param styleName
	 *            The name of the style
	 */
	public void setDefaultStyle(String layerName, String styleName) {
		String xml = "<layer><defaultStyle><name>"+styleName+"</name></defaultStyle></layer>";
		WebResource r = client.resource(geoserverURL + "/rest/layers/" + layerName + ".xml");
		r.type("application/xml").put(String.class, xml);
	}
	
	/**
	 * Set's whether the specified layer is enabled in GeoServer.
	 * 
	 * @param layerName
	 * @param enabled
	 */
	public void setLayerEnabled(String layerName, boolean enabled) {
		String xml = "<layer><enabled>"+enabled+"</enabled></layer>";
		WebResource r = client.resource(geoserverURL + "/rest/layers/" + layerName + ".xml");
		r.type("application/xml").put(String.class, xml);
	}

	/**
	 * TODO: document
	 * @param file
	 * @param workspace
	 * @param datastoreName
	 */
	public void uploadShapefile(File file, String workspace, String datastoreName) {
		WebResource r = client.resource(geoserverURL + "/rest/workspaces/" + workspace + "/datastores/" + datastoreName + "/file.shp");
		r.type("application/zip").put(File.class, file);
	}
	
	private void setAuthentication() {
		Authenticator.setDefault(new Authenticator() {
            @Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password.toCharArray());
			}
		});
	}
}
