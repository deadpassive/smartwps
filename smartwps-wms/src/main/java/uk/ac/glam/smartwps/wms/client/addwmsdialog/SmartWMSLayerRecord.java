package uk.ac.glam.smartwps.wms.client.addwmsdialog;

import uk.ac.glam.smartwps.wms.shared.WMSLayer;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * Record for the WMSLayerSelector.
 * 
 * @author Jon Britton
 */
public class SmartWMSLayerRecord extends ListGridRecord {

	private WMSLayer wmsLayer;

	/**
	 * Creates a new WMSLayerRecord for the given WMS layer.
	 * @param wmsLayer
	 */
	public SmartWMSLayerRecord(WMSLayer wmsLayer) {
		this.wmsLayer = wmsLayer;
		this.setAttribute("name", wmsLayer.getName());
		this.setAttribute("title", wmsLayer.getTitle());
		this.setAttribute("abstract", wmsLayer.getAbstract());
	}

	/**
	 * @return the WMS layer
	 */
	public WMSLayer getWMSLayer() {
		return wmsLayer;
	}
}
