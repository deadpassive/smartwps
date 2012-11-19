package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.response.ServiceResponse;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

@SuppressWarnings("serial")
public class WMSGetCapabilitiesResponse implements ServiceResponse {

	private List<WMSLayer> wmsLayers;

	public void setWMSLayers(List<WMSLayer> layers) {
		this.wmsLayers = new ArrayList<WMSLayer>(layers);
	}
	
	public List<WMSLayer> getWMSLayers() {
		return new ArrayList<WMSLayer>(wmsLayers);
	}
}
