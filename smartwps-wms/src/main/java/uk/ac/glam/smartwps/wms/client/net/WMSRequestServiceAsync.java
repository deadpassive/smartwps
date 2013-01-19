package uk.ac.glam.smartwps.wms.client.net;

import uk.ac.glam.smartwps.wms.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wms.shared.response.WMSGetCapabilitiesResponse;

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
