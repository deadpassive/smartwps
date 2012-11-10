package uk.ac.glam.smartwps.shared.request;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;
import uk.ac.glam.smartwps.shared.wcs111.CoverageDescription;

public class WCSGetCoverageAndStoreRequest extends ServiceRequest {

	private static final long serialVersionUID = -4461479472737582384L;
	
	private CoverageDescription coverageDescription;
	private BoundsSerializable bbox;

	/**
	 * The name of the new coverage
	 */
	private String layerName;
	
	public WCSGetCoverageAndStoreRequest() {
		super(null);
	}
		
	public WCSGetCoverageAndStoreRequest(CoverageDescription coverageDescription) {
		super(coverageDescription.getServiceURL());
		this.coverageDescription = coverageDescription;
		this.bbox = coverageDescription.getCoverageSummary().getWGS84BoundingBox();
	}
	
	public WCSGetCoverageAndStoreRequest(CoverageDescription coverageDescription, BoundsSerializable bbox) {
		this(coverageDescription);
		this.bbox = bbox;
	}
	
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String getLayerName() {
		return layerName;
	}

	public CoverageDescription getCoverageDescription() {
		return coverageDescription;
	}

	public BoundsSerializable getBoundingBox() {
		return bbox;
	}

}
