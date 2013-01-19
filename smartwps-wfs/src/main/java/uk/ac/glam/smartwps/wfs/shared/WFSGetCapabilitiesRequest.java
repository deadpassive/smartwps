package uk.ac.glam.smartwps.wfs.shared;

import uk.ac.glam.smartwps.base.shared.ServiceRequest;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WFSGetCapabilitiesRequest extends ServiceRequest {
	
	/**
	 * Empty constructor for serialisation.
	 */
	public WFSGetCapabilitiesRequest() {
		super(null);
	}

	/**
	 * TODO: document
	 * @param url
	 */
	public WFSGetCapabilitiesRequest(String url) {
		super(url);
	}

}
