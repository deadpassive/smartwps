package uk.ac.glam.smartwps.server.wfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.data.wfs.WFSDataStore;
import org.geotools.data.wfs.WFSDataStoreFactory;
import org.opengis.feature.simple.SimpleFeatureType;

import uk.ac.glam.smartwps.client.wfs.WFSConnectionException;
import uk.ac.glam.smartwps.server.ServerUtils;
import uk.ac.glam.smartwps.shared.request.WFSDescribeFeatureTypeRequest;
import uk.ac.glam.smartwps.shared.request.WFSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WFSDescribeFeatureTypeResponse;
import uk.ac.glam.smartwps.shared.response.WFSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wfs.WFSDataSource;
import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;
import uk.ac.glam.smartwps.shared.wfs.WFSFeatureTypeBase;


/**
 * Handles all communications between the application and WFS servers.
 * 
 * @author Jon Britton
 */
public class WFSHandler {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");
	private static WFSHandler instance;
	
	private HashMap<String, WFSDataStore> wfsDataStores;

	private WFSHandler() {
		wfsDataStores = new HashMap<String, WFSDataStore>();
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public static WFSHandler instance() {
		if (instance == null) {
			instance = new WFSHandler();
		}
		return instance;
	}
	
	private WFSDataStore loadWFSCapabilities(String url) throws WFSConnectionException {
		LOGGER.log(Level.INFO, "Loading WFS capabilities from {0}", url);
		Map<String, String> connectionParameters = new HashMap<String, String>();
		connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL",
				url);
		// Try to confuse the factory cache...
		// This is required or it might pull up an old WFSDataStore
		String random = String.valueOf(System.currentTimeMillis());
		connectionParameters.put("DummyParameter", random);
		
		WFSDataStore data = null;
		try {
			WFSDataStoreFactory factory = new WFSDataStoreFactory();
			data = factory.createDataStore(connectionParameters);
			//data = (WFSDataStore) DataStoreFinder
			//		.getDataStore(connectionParameters);
			wfsDataStores.put(url.split("\\?")[0], data);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error connecting to WFS server: " + e.getMessage(), e);
			throw new WFSConnectionException("Error connecting to WFS server: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Unexpected error", e);
			throw new WFSConnectionException("Unexpected error: " + e.getMessage());
		}
		// Store datastore in case we need it again later
		return data;
	}
	
	/**
	 * TODO: document
	 * @param request
	 * @return
	 * @throws WFSConnectionException
	 */
	public WFSGetCapabilitiesResponse wfsGetCapabilities(WFSGetCapabilitiesRequest request) throws WFSConnectionException {
		WFSGetCapabilitiesResponse response = new WFSGetCapabilitiesResponse();
		String url = request.getServiceUrl();
		LOGGER.log(Level.INFO, "WFS GetCapabilities with URL {0}", url);
		try {
			WFSDataStore data = loadWFSCapabilities(url);
			String[] typeNames = data.getTypeNames();
			ArrayList<WFSFeatureTypeBase> wfsLayers = new ArrayList<WFSFeatureTypeBase>();
			for (int i = 0; i < typeNames.length; i++) {
				String typeName = typeNames[i];

				WFSFeatureTypeBase newLayer = new WFSFeatureTypeBase();
				newLayer.setTypeName(typeName);
				newLayer.setName(typeName.split(":")[1]);
				newLayer.setTitle(data.getFeatureTypeTitle(typeName));
				newLayer.setAbstract(data.getFeatureTypeAbstract(typeName));
				newLayer.setWfsVersion(data.getInfo().getVersion());
				newLayer.setDataSource(createWFSDataSource(url));

				wfsLayers.add(newLayer);
			}
			response.setWFSLayers(wfsLayers);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error connecting to WFS server: " + e.getMessage(), e);
			throw new WFSConnectionException("Error connecting to WFS server: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Unexpected error: " + e.getMessage(), e);
			throw new WFSConnectionException("Unexpected error: " + e.getMessage());
		}

		return response;
	}
	
	/**
	 * Performs a WFS DescribeFeatureType request (actually called by GeoTools).
	 * If forceReloadCaps is true, a new GetCapabilities request is called. This
	 * is used when new data is expected to be added to a server (such as after a
	 * WPS execute request).
	 * @param url
	 * @param typeName
	 * @param forceReloadCaps
	 * @param retries
	 * @return
	 * @throws WFSConnectionException
	 */
	public WFSFeatureType wfsDescribeFeatureType(String url, String typeName, 
			boolean forceReloadCaps, int retries) throws WFSConnectionException {
		LOGGER.log(Level.INFO, "WFS DescribeFeatureType with URL {0} and typeName {1}", new Object[]{url, typeName});
		WFSDataStore data = null;
		try {
			boolean retry;
			do {
				retry = false;
				// Force reload?
				if (forceReloadCaps) {
					LOGGER.info("Reloading WFSCapabilities");
					data = loadWFSCapabilities(url);
				} else {
					// Check if we already have the datastore
					data = wfsDataStores.get(url.split("\\?")[0]);
					if (data == null) {
						LOGGER.info("WFS data store not found - adding.");
						data = loadWFSCapabilities(url);
					}
				}
				// If we haven't found the typename, or the data is null then retry
				int count = 0;
				if (((data == null) || (!ServerUtils.arrayContainsString(data.getTypeNames(), typeName))) && (count < retries)) {
					LOGGER.info("Problem finding typename in capabilities, retrying after 5secs");
                    // TODO: this is a crap way to do this. Do something else.
					Thread.sleep(5000);
					count++;
					retry = true;
				}
			} while (retry == true);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to load WFS capabilities: " + e.getMessage(), e);
			throw new WFSConnectionException("Failed to load WFS capabilities: " + e.getMessage());
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		
		if (data == null) {
			LOGGER.severe("Failed to load WFS capabilities");
			throw new WFSConnectionException("Failed to load WFS capabilities");
		}
			
		LOGGER.info("Creating new WFSFeatureType()");
		WFSFeatureType newLayer = new WFSFeatureType();
		newLayer.setTypeName(typeName);
		newLayer.setName(typeName.split(":")[1]);
		newLayer.setTitle(data.getFeatureTypeTitle(typeName));
		newLayer.setAbstract(data.getFeatureTypeAbstract(typeName));
		newLayer.setBounds(ServerUtils.boundsFromReferencedEnvelope(data
				.getFeatureTypeWGS84Bounds(typeName)));
		newLayer.setWfsVersion(data.getInfo().getVersion());
		newLayer.setDataSource(createWFSDataSource(url));
	
		// Now we get the namespace URI (this called DescribeFeatureType on the
		// WFS)
		SimpleFeatureType featureType;
		try {
			featureType = data.getSchema(typeName);
			newLayer.setNamespaceURI(featureType.getName().getNamespaceURI());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to load feature type details for "
							+ typeName + ": " + e.getMessage(), e);
			throw new WFSConnectionException("Failed to load feature type details for "
							+ typeName + ": " + e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to load feature type details for "
					+ typeName + ": " + e.getMessage(), e);
	throw new WFSConnectionException("Failed to load feature type details for "
					+ typeName + ": " + e.getMessage());
		}
		return newLayer;
	}
	
	/**
	 * TODO: document
	 * @param request
	 * @return
	 * @throws WFSConnectionException
	 */
	public WFSDescribeFeatureTypeResponse wfsDescribeFeatureType(WFSDescribeFeatureTypeRequest request) throws WFSConnectionException {
		LOGGER.log(Level.INFO, "In WFSDescribeFeatureType with {0}", request.getServiceUrl());
		WFSDescribeFeatureTypeResponse response = new WFSDescribeFeatureTypeResponse();
	
		WFSFeatureType featureType = wfsDescribeFeatureType(request.getServiceUrl(), request.getTypeName(), false, 0);
	
		response.setFeatureType(featureType);
		return response;
	}
	
	private static WFSDataSource createWFSDataSource(String url) {
		WFSDataSource dataSource = new WFSDataSource(url.split("\\?")[0]);
		
		// TODO: add WFS service info
		
		return dataSource;
	}
}
