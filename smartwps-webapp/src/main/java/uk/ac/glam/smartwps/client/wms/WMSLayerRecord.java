package uk.ac.glam.smartwps.client.wms;

import uk.ac.glam.smartwps.shared.wms.WMSLayer;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WMSLayerRecord extends ListGridRecord {

	private WMSLayer wmsLayer;

	/**
	 * TODO: document
	 * @param wmsLayer
	 */
	public WMSLayerRecord(WMSLayer wmsLayer) {
		this.wmsLayer = wmsLayer;
		this.setAttribute("name", wmsLayer.getName());
		this.setAttribute("title", wmsLayer.getTitle());
		this.setAttribute("abstract", wmsLayer.getLayerAbstract());
	}

	/**
	 * TODO: document
	 * @return
	 */
	public WMSLayer getWMSLayer() {
		return wmsLayer;
	}
}
