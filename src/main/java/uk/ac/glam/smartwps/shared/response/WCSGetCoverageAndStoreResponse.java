package uk.ac.glam.smartwps.shared.response;

import uk.ac.glam.smartwps.shared.wcs111.WCSCoverage;

public class WCSGetCoverageAndStoreResponse implements ServiceResponse {

	private static final long serialVersionUID = -8372066634527205187L;
	private WCSCoverage wcsCoverage;

	public void setWCSCoverage(WCSCoverage wcsCoverage) {
		this.wcsCoverage = wcsCoverage;
	}

	public WCSCoverage getWCSCoverage() {
		return wcsCoverage;
	}

}
