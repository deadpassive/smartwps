package uk.ac.glam.smartwps.client.net;

import uk.ac.glam.smartwps.client.wms.WMSConnectionException;
import uk.ac.glam.smartwps.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WMSGetCapabilitiesResponse;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@RemoteServiceRelativePath("wms")
public interface WMSRequestService extends RemoteService {
	
	/**
	 * TODO: document
	 * @param request
	 * @return the WMS capabilities metadata
	 * @throws WMSConnectionException
	 */
	WMSGetCapabilitiesResponse wmsGetCapabilities(WMSGetCapabilitiesRequest request)
			throws WMSConnectionException;

}
