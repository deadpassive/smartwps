package uk.ac.glam.smartwps.wms.shared.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.ac.glam.smartwps.base.shared.ServiceRequest;


/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WMSGetCapabilitiesRequest extends ServiceRequest {

	private List<String> layers = new ArrayList<String>();
	private boolean exactMatches;
	
	/**
	 * TODO: document
	 */
	public WMSGetCapabilitiesRequest() {
		super(null);
	}

	/**
	 * TODO: document
	 * @param url
	 */
	public WMSGetCapabilitiesRequest(String url) {
		super(url);
		exactMatches = true;
	}
	
	/**
	 * TODO: document
	 * @param url
	 * @param layers
	 */
	public WMSGetCapabilitiesRequest(String url, ArrayList<String> layers) {
		this(url);
		this.setLayers(layers);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public List<String> getLayers() {
		return new ArrayList<String>(layers);
	}

	/**
	 * TODO: document
	 * @param layers
	 */
	public final void setLayers(Collection<String> layers) {
		this.layers = new ArrayList<String>(layers);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public boolean isExactMatches() {
		return exactMatches;
	}

	/**
	 * TODO: document
	 * @param exactMatches
	 */
	public void setExactMatches(boolean exactMatches) {
		this.exactMatches = exactMatches;
	}
}