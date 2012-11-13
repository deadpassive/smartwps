package uk.ac.glam.smartwps.server;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class WPSProperties extends Properties {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");
	
	private static WPSProperties instance;

	private WPSProperties(RemoteServiceServlet servlet) {
		File f = new File(servlet.getServletContext().getRealPath("settings") + "/config.txt");
		LOGGER.log(Level.INFO, "Loading settings from file {0}", f.getAbsolutePath());

		try (FileInputStream in = new FileInputStream(f)) {
			load(in);
		} catch (Exception e) {
			LOGGER.severe("Cannot load config file. Using defaults");
			this.setProperty("GEOSERVER_URL", "http://localhost:8080/geoserver");
			this.setProperty("GEOSERVER_URL_PUBLIC", "http://localhost:8080/geoserver");
			this.setProperty("GEOSERVER_USERNAME", "admin");
			this.setProperty("GEOSERVER_PASSWORD", "geoserver");
		}
	}
	
	/**
	 * TODO: document
	 * @param servlet
	 */
	public static void initialiseProperties(RemoteServiceServlet servlet) {
		instance = new WPSProperties(servlet);
	}
	
	/**
	 * TODO: document
	 * @return the client properties
	 */
    public static WPSProperties getProperties() {
		return instance;
	}

	/**
	 * @return the local URL of the GeoServer which is used for data storage
	 */
	public String getGeoServerURL() {
		return this.getProperty("GEOSERVER_URL");
	}

	/**
	 * @return the publicaly accessible URL of the GeoServer which is used for data storage
	 */
	public String getPublicGeoServerURL() {
		return this.getProperty("GEOSERVER_URL_PUBLIC");
	}

	/**
	 * @return the username required to log into GeoServer
	 */
	public String getGeoserverUsername() {
		return this.getProperty("GEOSERVER_USERNAME");
	}
	
	/**
	 * @return the password required to log into GeoServer
	 */
	public String getGeoserverPassword() {
		return this.getProperty("GEOSERVER_PASSWORD");
	}

	/**
	 * Set the base URL of the application.
	 * @param moduleBaseURL the base URL for the client application
	 */
	public void setModuleBaseURL(String moduleBaseURL) {
		this.setProperty("MODULE_BASE_URL", moduleBaseURL);
	}
	
	/**
	 * @return the base URL of the client application
	 */
	public String getModuleBaseURL() {
		return this.getProperty("MODULE_BASE_URL"); 
	}
}
