package uk.ac.glam.smartwps.shared.response;

import uk.ac.glam.smartwps.shared.wcs111.WCSCapabilities111;

public class WCSCapabilitiesResponse implements ServiceResponse {

	private static final long serialVersionUID = -597795395412053268L;
	private WCSCapabilities111 wcsCaps;

	public void setWCSCapabilities(WCSCapabilities111 wcsCaps) {
		this.wcsCaps = wcsCaps;
	}
	
	public WCSCapabilities111 getWCSCapabilities() {
		return wcsCaps;
	}

}
