package uk.ac.glam.smartwps.shared.request;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WPSGetCapabilitiesRequest extends ServiceRequest {
	
	/**
     * Empty constructor for serialisation.
     */
	public WPSGetCapabilitiesRequest() {
		super(null);
	}

	/**
	 * TODO: document
	 * @param url
	 */
	public WPSGetCapabilitiesRequest(String url) {
		super(url);
	}

}
