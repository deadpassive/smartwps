package uk.ac.glam.smartwps.wms.shared.response;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.base.shared.ServiceResponse;
import uk.ac.glam.smartwps.wms.shared.WMSLayer;

/**
 * Used to send WMS layers between the server and the client.
 * 
 * @author Jon Britton
 */
public class WMSGetCapabilitiesResponse implements ServiceResponse {

	private List<WMSLayer> wmsLayers;

	/**
	 * Set the WMS layers to include in the response.
	 * @param layers the WMS layers
	 */
	public void setWMSLayers(List<WMSLayer> layers) {
		this.wmsLayers = new ArrayList<WMSLayer>(layers);
	}
	
	/**
	 * @return the WMS layers in the response
	 */
	public List<WMSLayer> getWMSLayers() {
		return new ArrayList<WMSLayer>(wmsLayers);
	}
}
