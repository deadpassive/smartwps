package uk.ac.glam.smartwps.client.datatree;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.menu.Menu;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WMSNode extends DataTreeNode {  
    private WMSLayer wmsLayer;
	
	/**
	 * TODO: document
	 * @param layer
	 */
	public WMSNode(WMSLayer layer) {
    	this.wmsLayer = layer;
        setAttribute("type", "WMS");
        setAttribute("name", layer.getName());
        addLayerToMap();
        setIcon("wmsicon.png");
		setAttribute("localname", layer.getName());
    }

	@Override
	public WMS getMapLayer() {
		return (WMS) mapLayer;
	}

	@Override
	void addLayerToMap() {
		addLayerToMap(null);
	}
	
	private void addLayerToMap(String[] styles) {
		// create new WMS layer
		GWT.log("Adding new layer: " + wmsLayer.getName());
		WMSParams wmsParams = new WMSParams();
		wmsParams.setFormat("image/png");
		wmsParams.setLayers(wmsLayer.getName());
		wmsParams.setTransparent(true);
		if ((styles != null) && (styles.length > 0)) {
			String stylesString = styles[0];
			for (int i = 1; i < styles.length; i++) {
				stylesString += "," + styles[i];
			}
			wmsParams.setStyles(stylesString);
		}
					
		WMSOptions wmsOptions = new WMSOptions();
		wmsOptions.setIsBaseLayer(false);
		wmsOptions.setProjection(SmartWPS.getSmartWPS().getMap().getProjection());
						
		mapLayer = new WMS(
				wmsLayer.getTitle(),
				wmsLayer.getServiceURL(),
				wmsParams,
				wmsOptions);
					
		SmartWPS.getSmartWPS().getMap().addLayer(mapLayer);
		
		GWT.log("Added new layer: " + wmsLayer.getName());
	}
	
	/**
	 * TODO: document
	 * @param styles
	 */
	public void setStyles(String[] styles) {
		// remove layer
		SmartWPS.getSmartWPS().getMap().removeLayer(mapLayer);
		// add layer with new styles
		addLayerToMap(styles);
	}

	/**
	 * @return the OpenLayers WMS layer wrapped by this node
	 */
	public WMSLayer getWMSLayer() {
		return wmsLayer;
	}
	
	@Override
	public String toString() {
		return wmsLayer.getName();
	}

	@Override
	public Bounds getExtent() {
		String projection = SmartWPS.getSmartWPS().getMap().getProjection();
		LonLat mins = new LonLat(wmsLayer.getBbox().getMinX(), wmsLayer
				.getBbox().getMinY());
		LonLat maxs = new LonLat(wmsLayer.getBbox().getMaxX(), wmsLayer
				.getBbox().getMaxY());
		mins.transform("EPSG:4326", projection);
		maxs.transform("EPSG:4326", projection);
		return new Bounds(mins.lon(), mins.lat(), maxs.lon(), maxs.lat());
	}

	@Override
	public Menu getContextMenu() {
		if (this.contextMenu == null)
			this.contextMenu = new WMSNodeContextMenu(this);
		return contextMenu;
	}
}

