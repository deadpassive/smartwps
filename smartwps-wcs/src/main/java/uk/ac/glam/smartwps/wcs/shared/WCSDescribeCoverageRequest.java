package uk.ac.glam.smartwps.wcs.shared;

import uk.ac.glam.smartwps.base.shared.ServiceRequest;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageSummary;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WCSDescribeCoverageRequest extends ServiceRequest {

	private String coverageID;
	private boolean reloadCaps;
	
	/**
	 * TODO: document
	 */
	public WCSDescribeCoverageRequest() {
		super(null);
	}
	
	/**
	 * TODO: document
	 * @param url
	 */
	public WCSDescribeCoverageRequest(String url) {
		super(url);
	}

	/**
	 * TODO: document
	 * @param coverageSummary
	 * @param reloadCaps
	 */
	public WCSDescribeCoverageRequest(CoverageSummary coverageSummary, boolean reloadCaps) {
		this(coverageSummary.getServiceURL());
		this.setCoverageID(coverageSummary.getIdentifier());
		this.setReloadCaps(reloadCaps);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getCoverageID() {
		return coverageID;
	}

	/**
	 * TODO: document
	 * @param coverageID
	 */
	public final void setCoverageID(String coverageID) {
		this.coverageID = coverageID;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public boolean isReloadCaps() {
		return reloadCaps;
	}

	/**
	 * TODO: document
	 * @param reloadCaps
	 */
	public final void setReloadCaps(boolean reloadCaps) {
		this.reloadCaps = reloadCaps;
	}

}
