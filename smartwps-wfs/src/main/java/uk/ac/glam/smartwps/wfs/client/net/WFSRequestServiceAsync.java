package uk.ac.glam.smartwps.wfs.client.net;

import com.google.gwt.user.client.rpc.AsyncCallback;

import uk.ac.glam.smartwps.wfs.shared.WFSDescribeFeatureTypeRequest;
import uk.ac.glam.smartwps.wfs.shared.WFSDescribeFeatureTypeResponse;
import uk.ac.glam.smartwps.wfs.shared.WFSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wfs.shared.WFSGetCapabilitiesResponse;


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
