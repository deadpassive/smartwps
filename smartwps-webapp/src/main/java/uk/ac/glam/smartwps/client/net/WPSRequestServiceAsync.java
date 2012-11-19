package uk.ac.glam.smartwps.client.net;

import uk.ac.glam.smartwps.shared.request.WPSExecuteRequest;
import uk.ac.glam.smartwps.shared.request.WPSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WPSExecuteResponse;
import uk.ac.glam.smartwps.shared.response.WPSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wps.DetailedProcessDescriptor;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author Jon Britton
 *
 */
public interface WPSRequestServiceAsync {

	/**
	 * TODO: document
	 * @param request
	 * @param callback
	 */
	void wpsGetCapabilities(WPSGetCapabilitiesRequest request, AsyncCallback<WPSGetCapabilitiesResponse> callback);
	
	/**
	 * TODO: document
	 * @param url
	 * @param id
	 * @param callback
	 */
	void getProcessDetails(String url, String id, AsyncCallback<DetailedProcessDescriptor> callback);
	
	/**
	 * TODO: document
	 * @param request
	 * @param callback
	 */
	void wpsExecute(WPSExecuteRequest request, AsyncCallback<WPSExecuteResponse> callback);
}
