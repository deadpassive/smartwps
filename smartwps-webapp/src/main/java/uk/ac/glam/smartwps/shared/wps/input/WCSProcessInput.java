package uk.ac.glam.smartwps.shared.wps.input;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;
import uk.ac.glam.smartwps.wcs.shared.WCSCoverage;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;

/**
 * A WCS coverage input to a WPS process.
 * 
 * @author Jon Britton
 */
public class WCSProcessInput extends ComplexProcessInput {
	
	private String serverUrl;
	private BoundsSerializable boundingBox;
	private String coverageIdentifier;
	private CoverageDescription coverageDescription;
	
	/**
	 * Empty constructor for serialisation.
	 */
	public WCSProcessInput(){
		super(null);
	}
	
	/**
	 * Create a new WPS input representing a WCS coverage.
	 * @param inputID the input ID
	 * @param coverage the coverage to use as input
	 */
	public WCSProcessInput(String inputID, WCSCoverage coverage) {
		super(inputID);
		this.coverageDescription = coverage.getCoverageDescription();
		this.coverageIdentifier = coverage.getCoverageDescription().getIdentifier();
		this.serverUrl = coverage.getCoverageDescription().getServiceURL();
		//TODO: should use another CRS?
		this.boundingBox = coverage.getCoverageDescription().getDefaultBoundingBox();
	}
	
	@Override
	public String toString() {
		return "id: " + getId() + "serverUrl: " + serverUrl + ", coverageId: " + coverageIdentifier + 
				", boundingBox: " + boundingBox;
	}
	
	/**
	 * @return the default bounds of this coverage
	 */
	public BoundsSerializable getBounds() {
		return boundingBox;
	}

	/**
	 * @return the WCS coverage meta-data
	 */
	public CoverageDescription getCoverageDescription() {
		return coverageDescription;
	}	
}
