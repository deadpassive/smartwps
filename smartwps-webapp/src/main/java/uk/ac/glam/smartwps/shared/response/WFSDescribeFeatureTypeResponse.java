package uk.ac.glam.smartwps.shared.response;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;

@SuppressWarnings("serial")
public class WFSDescribeFeatureTypeResponse implements ServiceResponse {

	private WFSFeatureType featureType;

	public void setFeatureType(WFSFeatureType featureType) {
		this.featureType = featureType;
	}

	public WFSFeatureType getFeatureType() {
		return featureType;
	}

}
