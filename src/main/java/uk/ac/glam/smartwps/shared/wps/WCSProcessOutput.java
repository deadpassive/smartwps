package uk.ac.glam.smartwps.shared.wps;

import uk.ac.glam.smartwps.shared.wcs111.WCSCoverage;

/**
 * A ProcessOutput containing a reference to a WFS service.
 * 
 * @author Jon
 *
 */
public class WCSProcessOutput extends ComplexProcessOutput {

	private static final long serialVersionUID = 7514847098440475584L;
	private WCSCoverage wcsCoverage;

	/**
	 * Gets the WCSCoverage associated with this process output
	 * @return WCS coverage
	 */
	public WCSCoverage getWcsCoverage() {
		return wcsCoverage;
	}

	/**
	 * Set the WCSCoverage to be used with this process output
	 * @param wcsCoverage
	 */
	public void setWcsCoverage(WCSCoverage wcsCoverage) {
		this.wcsCoverage = wcsCoverage;
	}

}
