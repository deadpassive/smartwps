package uk.ac.glam.smartwps.shared.response;

import uk.ac.glam.smartwps.wcs.shared.WCSCoverage;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WCSGetCoverageAndStoreResponse implements ServiceResponse {

	private WCSCoverage wcsCoverage;

	/**
	 * TODO: document
	 * @param wcsCoverage
	 */
	public void setWCSCoverage(WCSCoverage wcsCoverage) {
		this.wcsCoverage = wcsCoverage;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public WCSCoverage getWCSCoverage() {
		return wcsCoverage;
	}

}
