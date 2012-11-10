package uk.ac.glam.smartwps.client.datatree;

import uk.ac.glam.smartwps.shared.wcs111.WCSCoverage;

import com.smartgwt.client.widgets.menu.Menu;

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

	@Override
	public Menu getContextMenu() {
		return null;
	}
}
