package uk.ac.glam.smartwps.shared.response;

import uk.ac.glam.smartwps.shared.wcs111.WCSCapabilities111;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WCSCapabilitiesResponse implements ServiceResponse {

	private WCSCapabilities111 wcsCaps;

	/**
	 * TODO: document
	 * @param wcsCaps
	 */
	public void setWCSCapabilities(WCSCapabilities111 wcsCaps) {
		this.wcsCaps = wcsCaps;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public WCSCapabilities111 getWCSCapabilities() {
		return wcsCaps;
	}

}
