package uk.ac.glam.smartwps.shared.request;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureTypeBase;

@SuppressWarnings("serial")
public class WFSDescribeFeatureTypeRequest extends ServiceRequest {

    private String typeName;
	
	// Blank constructor for serialisation
	public WFSDescribeFeatureTypeRequest() {
		super(null);
	}

	public WFSDescribeFeatureTypeRequest(String url, String typeName) {
		super(url);
		this.typeName = typeName;
	}

	public WFSDescribeFeatureTypeRequest(WFSFeatureTypeBase featureTypeBase) {
		this(featureTypeBase.getServiceURL(),featureTypeBase.getTypeName());
	}

	public String getTypeName() {
		return typeName;
	}

}
