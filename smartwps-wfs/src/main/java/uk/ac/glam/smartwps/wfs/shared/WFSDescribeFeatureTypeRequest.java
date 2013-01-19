package uk.ac.glam.smartwps.wfs.shared;

import uk.ac.glam.smartwps.base.shared.ServiceRequest;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WFSDescribeFeatureTypeRequest extends ServiceRequest {

    private String typeName;
	
	/**
	 * Empty constructor for serialisation.
	 */
	public WFSDescribeFeatureTypeRequest() {
		super(null);
	}

	/**
	 * TODO: document
	 * @param url
	 * @param typeName
	 */
	public WFSDescribeFeatureTypeRequest(String url, String typeName) {
		super(url);
		this.typeName = typeName;
	}

	/**
	 * TODO: document
	 * @param featureTypeBase
	 */
	public WFSDescribeFeatureTypeRequest(WFSFeatureTypeBase featureTypeBase) {
		this(featureTypeBase.getServiceURL(),featureTypeBase.getTypeName());
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getTypeName() {
		return typeName;
	}

}
