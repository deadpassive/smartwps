package uk.ac.glam.smartwps.client.datatree;

import uk.ac.glam.smartwps.wcs.shared.WCSCoverage;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class CoverageNode extends WMSNode {

	private WCSCoverage coverage;

	/**
	 * TODO: document
	 * @param coverage
	 */
	public CoverageNode(WCSCoverage coverage) {
		super(coverage.getWmsLayer());
		this.coverage = coverage;
		setAttribute("type", "WCS");
        setAttribute("identifier", coverage.getCoverageDescription().getIdentifier());
        setIcon("wcsicon.png");
		setAttribute("localname", coverage.getCoverageDescription().getIdentifier());
	}
	
	/**
	 * @return the WCSCoverage object wrapped by this node
	 */
	public WCSCoverage getCoverage() {
		return coverage;
	}
}
