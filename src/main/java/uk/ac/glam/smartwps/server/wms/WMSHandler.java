package uk.ac.glam.smartwps.server.wms;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.data.ServiceInfo;
import org.geotools.data.ows.Layer;
import org.geotools.data.wms.WebMapServer;

import uk.ac.glam.smartwps.client.wms.WMSConnectionException;
import uk.ac.glam.smartwps.server.ServerUtils;
import uk.ac.glam.smartwps.shared.response.WMSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wms.WMSDataSource;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

public class WMSHandler {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");
	private static WMSHandler instance;
	
	private WMSHandler() {}
	
	public static WMSHandler instance() {
		if (instance == null) {
			instance = new WMSHandler();
		}
		return instance;
	}
	
	public WMSGetCapabilitiesResponse wmsGetCapabilities(String url,
			ArrayList<String> layers, boolean exactMatches) throws WMSConnectionException {
		WMSGetCapabilitiesResponse response = new WMSGetCapabilitiesResponse();

		LOGGER.info("Creating WMS GetCapabilities request for " + url);
		ArrayList<WMSLayer> selectedLayers = new ArrayList<WMSLayer>();

		WebMapServer wms = null;
		try {
			wms = new WebMapServer(new URL(url));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new WMSConnectionException("Failed to connect to WMS server: " + e.getMessage());
		}
		
		// DataSource
		WMSDataSource dataSource = createWMSDataSource(url, wms.getInfo());

		List<Layer> layerList = wms.getCapabilities().getLayerList();
		for (Iterator<Layer> iterator = layerList.iterator(); iterator
				.hasNext();) {
			Layer layer = iterator.next();
			if (layer.getName() != null) { // Named (viewable) layers only
				if (layers == null) { // Add all layers
					WMSLayer wmsLayer = WMSAdapter.layerAdapter(layer);
					wmsLayer.setDataSource(dataSource);
					selectedLayers.add(wmsLayer);
				} else { // Add similar layers
					for (Iterator<String> iterator2 = layers.iterator(); iterator2
							.hasNext();) {
						String l = iterator2.next();
						if (exactMatches) {
							if (layer.getName().equals(l)) { // check if
								// layers
								// have the
								// same name
								WMSLayer wmsLayer = WMSAdapter
										.layerAdapter(layer);;
								wmsLayer.setDataSource(dataSource);
								selectedLayers.add(wmsLayer);
							}
						} else {
							// this is a workaround for geoserver, which
							// names its wms laters as workspace:layername
							/*
							 * if (layer.getName().contains(l)) { // check
							 * if layers have similar names WMSLayer
							 * wmsLayer = WMSAdapter.layerAdapter(layer);
							 * wmsLayer.setServiceURL(url.split("\\?")[0]);
							 * selectedLayers.add(wmsLayer); }
							 */
							if ((ServerUtils.levenshteinDistance(layer.getName(), l)) < (Math
									.max(layer.getName().length(), l
											.length()) / 2)) {
								WMSLayer wmsLayer = WMSAdapter
										.layerAdapter(layer);
								wmsLayer.setDataSource(dataSource);
								selectedLayers.add(wmsLayer);
							}
						}
					}
				}
			}
		}
		response.setWMSLayers(selectedLayers);
		return response;
	}
	
	private WMSDataSource createWMSDataSource(String url, ServiceInfo info) {
		WMSDataSource dataSource = new WMSDataSource(url.split("\\?")[0]);
		
		// TODO: add WMS service info
		dataSource.setName(info.getTitle());
		
		return dataSource;
	}
	
}
