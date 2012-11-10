package uk.ac.glam.smartwps.shared.response;

import uk.ac.glam.smartwps.shared.wcs111.CoverageDescription;

public class WCSDescribeCoverageResponse implements ServiceResponse {

	private static final long serialVersionUID = 6452376985581123840L;
	private CoverageDescription coverageOffering;

	public void setCoverageOffering(CoverageDescription coverageOffering) {
		this.coverageOffering = coverageOffering;
	}
	
	public CoverageDescription getCoverageOffering() {
		return coverageOffering;
	}

}
