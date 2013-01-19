package uk.ac.glam.smartwps.wps.client.net;

import java.io.IOException;

import uk.ac.glam.smartwps.base.shared.RESTConnectionException;
import uk.ac.glam.smartwps.wcs.shared.WCSConnectionException;
import uk.ac.glam.smartwps.wfs.shared.WFSConnectionException;
import uk.ac.glam.smartwps.wms.shared.WMSConnectionException;
import uk.ac.glam.smartwps.wps.shared.DetailedProcessDescriptor;
import uk.ac.glam.smartwps.wps.shared.WPSConnectionException;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteException;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteRequest;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteResponse;
import uk.ac.glam.smartwps.wps.shared.WPSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wps.shared.WPSGetCapabilitiesResponse;

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
