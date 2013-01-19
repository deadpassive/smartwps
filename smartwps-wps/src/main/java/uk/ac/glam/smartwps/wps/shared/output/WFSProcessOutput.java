package uk.ac.glam.smartwps.wps.shared.output;

import uk.ac.glam.smartwps.wfs.shared.WFSFeatureType;

/**
 * A WPS process output containing a reference to a WFS service.
 * 
 * @author Jon Britton
 */
public class WFSProcessOutput extends ComplexProcessOutput {

	private WFSFeatureType featureType;

	/**
	 * @return the WFSFeatureType to be used for this process output.
	 */
	public WFSFeatureType getFeatureType() {
		return featureType;
	}

	/**
	 * Sets the WFSFeatureType to be used for this process output.
	 * @param featureType
	 */
	public void setFeatureType(WFSFeatureType featureType) {
		this.featureType = featureType;
	}
}
