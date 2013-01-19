package uk.ac.glam.smartwps.wms.client.net;

import uk.ac.glam.smartwps.wms.shared.WMSConnectionException;
import uk.ac.glam.smartwps.wms.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wms.shared.response.WMSGetCapabilitiesResponse;

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
	 * @return
	 * @throws WMSConnectionException
	 */
	WMSGetCapabilitiesResponse wmsGetCapabilities(WMSGetCapabilitiesRequest request)
			throws WMSConnectionException;

}
