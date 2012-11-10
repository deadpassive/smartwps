package uk.ac.glam.smartwps.shared.wps.input;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;
import uk.ac.glam.smartwps.shared.wcs111.CoverageDescription;
import uk.ac.glam.smartwps.shared.wcs111.WCSCoverage;

public class WCSProcessInput extends ComplexProcessInput {
	
	private static final long serialVersionUID = 4246921982702145698L;
	private String serverUrl;
	private BoundsSerializable boundingBox;
	private String coverageIdentifier;
	private CoverageDescription coverageDescription;
	
	public WCSProcessInput(){}
	
	public WCSProcessInput(String inputID, WCSCoverage coverage) {
		this.coverageDescription = coverage.getCoverageDescription();
		setId(inputID);
		this.coverageIdentifier = coverage.getCoverageDescription().getIdentifier();
		this.serverUrl = coverage.getCoverageDescription().getServiceURL();
		//TODO: should use another CRS?
		this.boundingBox = coverage.getCoverageDescription().getDefaultBoundingBox();
	}
	
	public String toString() {
		return "id: " + getId() + "serverUrl: " + serverUrl + ", coverageId: " + coverageIdentifier + 
				", boundingBox: " + boundingBox;
	}

	public void setBounds(BoundsSerializable bounds) {
		this.boundingBox = bounds;
	}
	
	public BoundsSerializable getBounds() {
		return boundingBox;
	}

	public CoverageDescription getCoverageDescription() {
		return coverageDescription;
	}	
}
