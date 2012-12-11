package uk.ac.glam.smartwps.client.addwmsdialog;

import uk.ac.glam.smartwps.shared.wms.WMSLayer;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * Record for the WMSLayerSelector.
 * 
 * @author Jon Britton
 */
public class WMSLayerRecord extends ListGridRecord {

	private WMSLayer wmsLayer;

	/**
	 * Creates a new WMSLayerRecord for the given WMS layer.
	 * @param wmsLayer
	 */
	public WMSLayerRecord(WMSLayer wmsLayer) {
		this.wmsLayer = wmsLayer;
		this.setAttribute("name", wmsLayer.getName());
		this.setAttribute("title", wmsLayer.getTitle());
		this.setAttribute("abstract", wmsLayer.getLayerAbstract());
	}

	/**
	 * @return the WMS layer
	 */
	public WMSLayer getWMSLayer() {
		return wmsLayer;
	}
}
