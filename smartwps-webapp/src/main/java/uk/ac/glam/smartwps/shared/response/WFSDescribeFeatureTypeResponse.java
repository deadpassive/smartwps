package uk.ac.glam.smartwps.shared.response;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WFSDescribeFeatureTypeResponse implements ServiceResponse {

	private WFSFeatureType featureType;

	/**
	 * TODO: document
	 * @param featureType
	 */
	public void setFeatureType(WFSFeatureType featureType) {
		this.featureType = featureType;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public WFSFeatureType getFeatureType() {
		return featureType;
	}

}
