package uk.ac.glam.smartwps.wms.shared.response;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.response.ServiceResponse;
import uk.ac.glam.smartwps.wms.shared.WMSLayer;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class WMSGetCapabilitiesResponse implements ServiceResponse {

	private List<WMSLayer> wmsLayers;

	/**
	 * TODO: document
	 * @param layers
	 */
	public void setWMSLayers(List<WMSLayer> layers) {
		this.wmsLayers = new ArrayList<WMSLayer>(layers);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public List<WMSLayer> getWMSLayers() {
		return new ArrayList<WMSLayer>(wmsLayers);
	}
}
