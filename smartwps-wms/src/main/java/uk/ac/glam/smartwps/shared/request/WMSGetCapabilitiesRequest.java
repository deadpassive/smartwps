package uk.ac.glam.smartwps.shared.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.ac.glam.smartwps.shared.request.ServiceRequest;

@SuppressWarnings("serial")
public class WMSGetCapabilitiesRequest extends ServiceRequest {

	private List<String> layers = new ArrayList<String>();
	private boolean exactMatches;
	
	public WMSGetCapabilitiesRequest() {
		super(null);
	}

	public WMSGetCapabilitiesRequest(String url) {
		super(url);
		exactMatches = true;
	}
	
	public WMSGetCapabilitiesRequest(String url, ArrayList<String> layers) {
		this(url);
		this.setLayers(layers);
	}

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

	public boolean isExactMatches() {
		return exactMatches;
	}

	public void setExactMatches(boolean exactMatches) {
		this.exactMatches = exactMatches;
	}

}
