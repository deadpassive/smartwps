package uk.ac.glam.smartwps.wcs.shared;

import uk.ac.glam.smartwps.base.shared.ServiceResponse;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WCSDescribeCoverageResponse implements ServiceResponse {

	private CoverageDescription coverageOffering;

	/**
	 * TODO: document
	 * @param coverageOffering
	 */
	public void setCoverageOffering(CoverageDescription coverageOffering) {
		this.coverageOffering = coverageOffering;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public CoverageDescription getCoverageOffering() {
		return coverageOffering;
	}

}
