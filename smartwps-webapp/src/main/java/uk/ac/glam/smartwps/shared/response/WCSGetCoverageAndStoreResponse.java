package uk.ac.glam.smartwps.shared.response;

import uk.ac.glam.smartwps.shared.wcs111.WCSCoverage;

@SuppressWarnings("serial")
public class WCSGetCoverageAndStoreResponse implements ServiceResponse {

	private WCSCoverage wcsCoverage;

	public void setWCSCoverage(WCSCoverage wcsCoverage) {
		this.wcsCoverage = wcsCoverage;
	}

	public WCSCoverage getWCSCoverage() {
		return wcsCoverage;
	}

}
