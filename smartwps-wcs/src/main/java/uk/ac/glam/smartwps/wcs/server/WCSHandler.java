package uk.ac.glam.smartwps.wcs.server;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.data.Query;

import uk.ac.glam.smartwps.base.server.GeoServerREST;
import uk.ac.glam.smartwps.base.server.ServerUtils;
import uk.ac.glam.smartwps.base.server.WPSProperties;
import uk.ac.glam.smartwps.base.shared.RESTConnectionException;
import uk.ac.glam.smartwps.wcs.shared.WCSCapabilitiesResponse;
import uk.ac.glam.smartwps.wcs.shared.WCSConnectionException;
import uk.ac.glam.smartwps.wcs.shared.WCSCoverage;
import uk.ac.glam.smartwps.wcs.shared.WCSDescribeCoverageResponse;
import uk.ac.glam.smartwps.wcs.shared.WCSGetCoverageAndStoreRequest;
import uk.ac.glam.smartwps.wcs.shared.WCSGetCoverageAndStoreResponse;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;
import uk.ac.glam.smartwps.wcs.shared.v111.WCSCapabilities111;
import uk.ac.glam.smartwps.wcs.shared.v111.WCSDataSource;
import uk.ac.glam.smartwps.wms.server.WMSHandler;
import uk.ac.glam.smartwps.wms.shared.WMSConnectionException;
import uk.ac.glam.smartwps.wms.shared.WMSLayer;
import uk.ac.glam.wcsclient.StoredCoverage;
import uk.ac.glam.wcsclient.WCS111;
import uk.ac.glam.wcsclient.WebCoverageService;

/**
 * Handles all communications between the application and WCS servers.
 * 
 * @author Jon Britton
 */
public class WCSHandler {

	private static final Logger LOGGER = Logger.getLogger("smartwps.server");
	private static WCSHandler instance;
	
	private HashMap<String, WCS111> webCoverageServices;
	private GeoServerREST geoserverREST;
	private WPSProperties properties;
	
	private WCSHandler() {
		webCoverageServices = new HashMap<String, WCS111>();
		// Properties should have been initialised by now
		properties = WPSProperties.getProperties();
		geoserverREST = new GeoServerREST(
				properties.getGeoServerURL(),
				properties.getGeoserverUsername(),
				properties.getGeoserverPassword());
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public static WCSHandler instance() {
		if (instance == null) {
			instance = new WCSHandler();
		}
		return instance;
	}
	
	private WCS111 wcsGetCaps1_1_1(final String url) throws WCSConnectionException {
		boolean hasRequest = false;
		boolean hasVersion = false;
		boolean hasService = false;
        
        StringBuilder urlBuilder = new StringBuilder(url);
	
		// Check if params are present
		if (url.contains("?")) {
			// In case we have a trailing ?...
			if (url.indexOf('?') < url.length() - 1) {
				String[] params = url.split("\\?")[1].split("&");
				for (int i = 0; i < params.length; i++) {
					if (params[i].contains("=")) { // use if to avoid
						// ArrayIndexOutOfBoundsException...
						String param = params[i].split("=")[0];
	
						if (param.equalsIgnoreCase("REQUEST")) {
                            hasRequest = true;
                        } else if (param.equalsIgnoreCase("SERVICE")) {
                            hasService = true;
                        } else if (param.equalsIgnoreCase("VERSION")) {
                            hasVersion = true;
                        }
					}
				}
			}
		} else { // no params
			urlBuilder.append("?");
		}
	
		// create missing params
		if (!hasService) {
            urlBuilder.append("&SERVICE=WCS");
        }
		if (!hasRequest) {
            urlBuilder.append("&REQUEST=GetCapabilities");
        }
		if (!hasVersion) {
			urlBuilder.append("&VERSION=1.1.1");
		}
        
        String finalUrl = urlBuilder.toString();
	
		LOGGER.log(Level.INFO, "Final URL: {0}", finalUrl);
	
		WCS111 wcs = null;
		try {
			wcs = (WCS111) WebCoverageService.createWebCoverageService(finalUrl);
			webCoverageServices.put(finalUrl.split("\\?")[0], wcs);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new WCSConnectionException(e.getMessage(), ServerUtils.getStackTraceAsString(e));
		}
		
		return wcs;
	}
	
	/**
	 * TODO: document
	 * @param url
	 * @return
	 * @throws WCSConnectionException
	 */
	public WCSCapabilitiesResponse wcsGetCapabilities(final String url) throws WCSConnectionException {
		WCSCapabilitiesResponse response = new WCSCapabilitiesResponse();
		
		// Only support 1.1.1 for now...
		WCS111 wcs = wcsGetCaps1_1_1(url);
		
		WCSCapabilities111 wcsCaps = WCS111Adapter.parseWCSCapabilities(wcs);
		response.setWCSCapabilities(wcsCaps);
			
		return response;
	}
	
	/**
	 * TODO: document
	 * @param url
	 * @param coverage
	 * @param reloadCaps
	 * @return
	 * @throws WCSConnectionException
	 */
	public WCSDescribeCoverageResponse wcsDescribeCoverage(final String url, String coverage, boolean reloadCaps) 
            throws WCSConnectionException {
		WCSDescribeCoverageResponse response = new WCSDescribeCoverageResponse();
        String finalUrl = url.split("\\?")[0];
		
		WCS111 wcs;
		if (reloadCaps || (webCoverageServices.get(finalUrl) != null)) {
			wcs = wcsGetCaps1_1_1(finalUrl);
		} else {
			wcs = webCoverageServices.get(finalUrl);
		}

		CoverageDescription coverageOffering = null;
		try {
			coverageOffering = WCS111Adapter.parseDescribeCoverage(wcs, coverage);
		} catch (IOException e) {
			throw new WCSConnectionException("Failed carrying out WCS DescribeCoverage request for coverage "
							+ coverage + " at " + finalUrl);
		}
		response.setCoverageOffering(coverageOffering);
		return response;
	}
	
	/**
	 * TODO: document
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws WMSConnectionException
	 * @throws RESTConnectionException
	 * @throws WCSConnectionException
	 */
	public WCSGetCoverageAndStoreResponse wcsGetCoverageAndStore(WCSGetCoverageAndStoreRequest request) 
            throws IOException, WMSConnectionException, RESTConnectionException, WCSConnectionException {
		WCSGetCoverageAndStoreResponse response = new WCSGetCoverageAndStoreResponse();
		
		//Get existing WCS?
		String url = request.getCoverageDescription().getServiceURL();
		WCS111 wcs = webCoverageServices.get(url);
		if (wcs == null) {
			// Not found, do a GetCapabilities to create the WCS.
			String splitUrl = url.split("\\?")[0];
			wcs = wcsGetCaps1_1_1(splitUrl);
		}
		
		//Get bbox string
		String bbox = request.getBoundingBox().getWCS111FormattedString();
		
		//Call GetCoverage
		StoredCoverage remoteCoverage = wcs.getCoverageAndStore(request.getLayerName(), bbox, "geotiff");
		
		//Add coverage to local geoserver
		WCSCoverage coverage = addCoverageToGeoServer(remoteCoverage.getCoverageHref(), "swps", request.getLayerName());
		coverage.setDataSource(new WCSDataSource(wcs.getServiceURL()));
		response.setWCSCoverage(coverage);
		return response;
	}
	
	/**
	 * Adds a remote coverage to the local GeoServer.
	 * 
	 * @param coverageUrl The location of the coverage
     * @param workspace 
     * @param layerName 
     * @return
	 * @throws IOException
	 * @throws WMSConnectionException 
	 * @throws RESTConnectionException 
	 * @throws WCSConnectionException 
	 */
	public WCSCoverage addCoverageToGeoServer(String coverageUrl, String workspace, String layerName) 
            throws IOException,
			WMSConnectionException, RESTConnectionException, WCSConnectionException {
		// Create swps workspace
		geoserverREST.createWorkspace(workspace);

		// Download file
		URL url = new URL(coverageUrl);
		File file = ServerUtils.writeToFile(url.openStream(), layerName + ".tiff");

		// Add file to geoserver
		geoserverREST.uploadCoverage(file, workspace, layerName, layerName);

		// All layer to use all styles on the server
//		geoserverREST.setAllStylesForLayer(layerName);

		// Set default style
		geoserverREST.setDefaultStyle(layerName, "raster");
		
		Query query;

		// Enable layer
		geoserverREST.setLayerEnabled(layerName, true);

		// Create WMSLayer for new layer
		ArrayList<String> wmsLayers = new ArrayList<String>();
		wmsLayers.add(layerName);
		// There should only be one WMSLayer in the response
		// WCS module depends on WMS module...
		WMSLayer wmsLayer = WMSHandler.instance().wmsGetCapabilities(
				properties.getPublicGeoServerURL() + "/" + workspace + "/ows",
				wmsLayers, true).getWMSLayers().get(0);

		// TODO: allow the original coverage to be used in processes?? Current approach uses copied version
		// which might not be exactly the same
		
		// Set the publicaly accessible URL
		//wmsLayer.setServiceURL(properties.getPublicGeoServerURL() + "/" + workspace + "/wms");
		// Create a coverage for new layer
		CoverageDescription coverageOffering = wcsDescribeCoverage(
				properties.getPublicGeoServerURL() + "/wcs", layerName, true)
				.getCoverageOffering();
		WCSCoverage wcsCoverage = new WCSCoverage(coverageOffering, wmsLayer);

		return wcsCoverage;
	}
	
	/**
	 * TODO: document
	 * @param wcs
	 * @param id
	 * @param bbox
	 * @param format
	 * @return
	 * @throws IOException
	 */
	public StoredCoverage wcsGetCoverage(WCS111 wcs, String id, String bbox, String format) throws IOException {
		return wcs.getCoverageAndStore(id, bbox, format);
	}
	
	/**
	 * TODO: document
	 * @param url
	 * @return
	 */
	public WCS111 getWCS(String url) {
		return webCoverageServices.get(url);
	}
}
