package uk.ac.glam.smartwps.shared.request;

@SuppressWarnings("serial")
public class WCSGetCapabilitiesRequest extends ServiceRequest {
	
	public WCSGetCapabilitiesRequest() {
		super(null);
	}

	public WCSGetCapabilitiesRequest(String url) {
		super(url);
	}

}
