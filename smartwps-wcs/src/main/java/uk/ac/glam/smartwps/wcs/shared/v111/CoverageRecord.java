package uk.ac.glam.smartwps.wcs.shared.v111;


import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * A record for displaying data from a CoverageOfferingBrief object. Intended for use in a AddCoverageListGrid.
 * 
 * @author Jon Britton
 */
public class CoverageRecord extends ListGridRecord {

	private CoverageSummary coverageSummary;

	/**
	 * Creates a new CoverageRecord for the given CoverageOfferingBrief.
	 * 
	 * @param coverageSummary coverage info
	 */
	public CoverageRecord(CoverageSummary coverageSummary) {
		this.coverageSummary = coverageSummary;
		this.setAttribute("abstract", coverageSummary.getAbstract());
		this.setAttribute("identifier", coverageSummary.getIdentifier());
		this.setAttribute("title", coverageSummary.getTitle());
	}
	
	/**
	 * Returns the CoverageOfferingBrief object associated with this record.
	 * @return
	 */
	public CoverageSummary getCoverageSummary() {
		return coverageSummary;
	}

}
