package uk.ac.glam.smartwps.client.net;

import uk.ac.glam.smartwps.shared.request.WFSDescribeFeatureTypeRequest;
import uk.ac.glam.smartwps.shared.request.WFSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WFSDescribeFeatureTypeResponse;
import uk.ac.glam.smartwps.shared.response.WFSGetCapabilitiesResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: document
 * @author Jon Britton
 *
 */
public interface WFSRequestServiceAsync {
	
	/**
	 * TODO: document
	 * @param request
	 * @param callback
	 */
	void wfsGetCapabilities(WFSGetCapabilitiesRequest request, AsyncCallback<WFSGetCapabilitiesResponse> callback);
	
	/**
	 * TODO: document
	 * @param request
	 * @param callback
	 */
	void wfsDescribeFeatureType(WFSDescribeFeatureTypeRequest request, 
            AsyncCallback<WFSDescribeFeatureTypeResponse> callback);
}
