package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;

import uk.ac.glam.smartwps.shared.wms.WMSLayer;

public class WMSGetCapabilitiesResponse implements ServiceResponse {

	private static final long serialVersionUID = 2496044446779200537L;
	private ArrayList<WMSLayer> wmsLayers;

	public void setWMSLayers(ArrayList<WMSLayer> layers) {
		this.wmsLayers = layers;
	}
	
	public ArrayList<WMSLayer> getWMSLayers() {
		return wmsLayers;
	}

}
