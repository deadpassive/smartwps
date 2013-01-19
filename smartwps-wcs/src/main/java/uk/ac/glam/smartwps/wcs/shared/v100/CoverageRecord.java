package uk.ac.glam.smartwps.wcs.shared.v100;


import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * A record for displaying data from a CoverageOfferingBrief object. Intended for use in a AddCoverageListGrid.
 * 
 * @author Jon Britton
 */
public class CoverageRecord extends ListGridRecord {

	private CoverageOfferingBrief coverageOfferingBrief;

	/**
	 * Creates a new CoverageRecord for the given CoverageOfferingBrief.
	 * 
	 * @param coverageOfferingBrief coverage info
	 */
	public CoverageRecord(CoverageOfferingBrief coverageOfferingBrief) {
		this.coverageOfferingBrief = coverageOfferingBrief;
		this.setAttribute("description", coverageOfferingBrief.getDescription());
		this.setAttribute("name", coverageOfferingBrief.getName());
		this.setAttribute("label", coverageOfferingBrief.getLabel());
	}
	
	/**
	 * Returns the CoverageOfferingBrief object associated with this record.
	 * @return
	 */
	public CoverageOfferingBrief getCoverageOfferingBrief() {
		return coverageOfferingBrief;
	}

}
