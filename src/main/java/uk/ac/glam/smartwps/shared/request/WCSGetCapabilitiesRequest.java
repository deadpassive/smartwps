package uk.ac.glam.smartwps.shared.request;

public class WCSGetCapabilitiesRequest extends ServiceRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4085124204292456308L;
	
	public WCSGetCapabilitiesRequest() {
		super(null);
	}

	public WCSGetCapabilitiesRequest(String url) {
		super(url);
	}

}
