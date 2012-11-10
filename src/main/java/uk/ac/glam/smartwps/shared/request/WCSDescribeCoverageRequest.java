package uk.ac.glam.smartwps.shared.request;

import uk.ac.glam.smartwps.shared.wcs111.CoverageSummary;

public class WCSDescribeCoverageRequest extends ServiceRequest {

	private static final long serialVersionUID = 7181975693226145838L;
	private String coverageID;
	private boolean reloadCaps;
	
	public WCSDescribeCoverageRequest() {
		super(null);
	}
	
	public WCSDescribeCoverageRequest(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public WCSDescribeCoverageRequest(CoverageSummary coverageSummary, boolean reloadCaps) {
		this(coverageSummary.getServiceURL());
		this.setCoverageID(coverageSummary.getIdentifier());
		this.setReloadCaps(reloadCaps);
	}

	public String getCoverageID() {
		return coverageID;
	}

	public void setCoverageID(String coverageID) {
		this.coverageID = coverageID;
	}

	public boolean isReloadCaps() {
		return reloadCaps;
	}

	public void setReloadCaps(boolean reloadCaps) {
		this.reloadCaps = reloadCaps;
	}

}
