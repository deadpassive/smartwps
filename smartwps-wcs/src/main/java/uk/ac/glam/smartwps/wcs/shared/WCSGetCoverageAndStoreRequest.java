package uk.ac.glam.smartwps.wcs.shared;

import uk.ac.glam.smartwps.base.shared.ServiceRequest;
import uk.ac.glam.smartwps.base.shared.ows.BoundsSerializable;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WCSGetCoverageAndStoreRequest extends ServiceRequest {
	
	private CoverageDescription coverageDescription;
	private BoundsSerializable bbox;

	/**
	 * The name of the new coverage
	 */
	private String layerName;
	
	/**
	 * TODO: document
	 */
	public WCSGetCoverageAndStoreRequest() {
		super(null);
	}
		
	/**
	 * TODO: document
	 * @param coverageDescription
	 */
	public WCSGetCoverageAndStoreRequest(CoverageDescription coverageDescription) {
		super(coverageDescription.getServiceURL());
		this.coverageDescription = coverageDescription;
		this.bbox = coverageDescription.getCoverageSummary().getWGS84BoundingBox();
	}
	
	/**
	 * TODO: document
	 * @param coverageDescription
	 * @param bbox
	 */
	public WCSGetCoverageAndStoreRequest(CoverageDescription coverageDescription, BoundsSerializable bbox) {
		this(coverageDescription);
		this.bbox = bbox;
	}
	
	/**
	 * TODO: document
	 * @param layerName
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getLayerName() {
		return layerName;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public CoverageDescription getCoverageDescription() {
		return coverageDescription;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public BoundsSerializable getBoundingBox() {
		return bbox;
	}

}
