package uk.ac.glam.smartwps.wps.client.net;

import uk.ac.glam.smartwps.wps.shared.DetailedProcessDescriptor;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteRequest;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteResponse;
import uk.ac.glam.smartwps.wps.shared.WPSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wps.shared.WPSGetCapabilitiesResponse;

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
