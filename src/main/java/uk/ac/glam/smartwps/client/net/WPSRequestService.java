package uk.ac.glam.smartwps.client.net;

import java.io.IOException;

import uk.ac.glam.smartwps.client.RESTConnectionException;
import uk.ac.glam.smartwps.client.wcs.WCSConnectionException;
import uk.ac.glam.smartwps.client.wfs.WFSConnectionException;
import uk.ac.glam.smartwps.client.wms.WMSConnectionException;
import uk.ac.glam.smartwps.client.wps.WPSConnectionException;
import uk.ac.glam.smartwps.client.wps.WPSExecuteException;
import uk.ac.glam.smartwps.shared.request.WPSExecuteRequest;
import uk.ac.glam.smartwps.shared.request.WPSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WPSExecuteResponse;
import uk.ac.glam.smartwps.shared.response.WPSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wps.DetailedProcessDescriptor;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * TODO: document
 * @author Jon Britton
 * 
 */
@RemoteServiceRelativePath("wps")
public interface WPSRequestService extends RemoteService {

	/**
	 * TODO: document
	 * @param request
	 * @return
	 * @throws WPSConnectionException
	 */
	WPSGetCapabilitiesResponse wpsGetCapabilities(WPSGetCapabilitiesRequest request)
			throws WPSConnectionException;
	
	/**
	 * TODO: document
	 * @param url
	 * @param id
	 * @return
	 */
	DetailedProcessDescriptor getProcessDetails(String url, String id);

	/**
	 * TODO: document
	 * @param request
	 * @return
	 * @throws WPSConnectionException
	 * @throws WMSConnectionException
	 * @throws WPSExecuteException
	 * @throws RESTConnectionException
	 * @throws IOException
	 * @throws WCSConnectionException
	 * @throws WFSConnectionException
	 */
	WPSExecuteResponse wpsExecute(WPSExecuteRequest request)
			throws WPSConnectionException, WMSConnectionException,
			WPSExecuteException, RESTConnectionException, IOException,
			WCSConnectionException, WFSConnectionException;
}
