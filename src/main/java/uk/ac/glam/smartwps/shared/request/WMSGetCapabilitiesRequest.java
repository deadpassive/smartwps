package uk.ac.glam.smartwps.shared.request;

import java.util.ArrayList;

public class WMSGetCapabilitiesRequest extends ServiceRequest {

	private static final long serialVersionUID = -613943240900456850L;
	private ArrayList<String> layers;
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

	public ArrayList<String> getLayers() {
		return layers;
	}

	public void setLayers(ArrayList<String> layers) {
		this.layers = layers;
	}

	public boolean isExactMatches() {
		return exactMatches;
	}

	public void setExactMatches(boolean exactMatches) {
		this.exactMatches = exactMatches;
	}

}
