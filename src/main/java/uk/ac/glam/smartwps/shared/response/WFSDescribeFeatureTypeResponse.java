package uk.ac.glam.smartwps.shared.response;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;

public class WFSDescribeFeatureTypeResponse implements ServiceResponse {

	private static final long serialVersionUID = 3584214499875134160L;
	private WFSFeatureType featureType;

	public void setFeatureType(WFSFeatureType featureType) {
		this.featureType = featureType;
	}

	public WFSFeatureType getFeatureType() {
		return featureType;
	}

}
