package uk.ac.glam.smartwps.client.net;

import uk.ac.glam.smartwps.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WMSGetCapabilitiesResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public interface WMSRequestServiceAsync {

	/**
	 * TODO: document
	 * @param request
	 * @param callback
	 */
	void wmsGetCapabilities(WMSGetCapabilitiesRequest request, AsyncCallback<WMSGetCapabilitiesResponse> callback);
}
