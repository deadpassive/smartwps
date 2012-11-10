package uk.ac.glam.smartwps.shared.wps.output;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;

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
