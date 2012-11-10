package uk.ac.glam.smartwps.shared.wps;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;

/**
 * A ProcessOutput containing a reference to a WFS service.
 * 
 * @author Jon
 *
 */
public class WFSProcessOutput extends ComplexProcessOutput {

	WFSFeatureType featureType;

	/**
	 * Gets the WFSFeatureType to be used for this process output.
	 * @return
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
