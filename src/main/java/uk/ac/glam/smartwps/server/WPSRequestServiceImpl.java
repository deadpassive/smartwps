package uk.ac.glam.smartwps.server;

import java.io.IOException;

import uk.ac.glam.smartwps.client.RESTConnectionException;
import uk.ac.glam.smartwps.client.net.WPSRequestService;
import uk.ac.glam.smartwps.client.wcs.WCSConnectionException;
import uk.ac.glam.smartwps.client.wfs.WFSConnectionException;
import uk.ac.glam.smartwps.client.wps.WPSConnectionException;
import uk.ac.glam.smartwps.client.wps.WPSExecuteException;
import uk.ac.glam.smartwps.server.wps.WPSHandler;
import uk.ac.glam.smartwps.shared.request.WPSExecuteRequest;
import uk.ac.glam.smartwps.shared.request.WPSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WPSExecuteResponse;
import uk.ac.glam.smartwps.shared.response.WPSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wps.DetailedProcessDescriptor;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Contains a number of GWT RPC methods for accessing Open Web Services.
 * 
 * @author Jon
 * 
 */
@SuppressWarnings("serial")
public class WPSRequestServiceImpl extends RemoteServiceServlet implements
		WPSRequestService {

	@Override
	public WPSGetCapabilitiesResponse wpsGetCapabilities(WPSGetCapabilitiesRequest request) 
            throws WPSConnectionException {
		return WPSHandler.instance().wpsGetCapabilities(request);
	}

	@Override
	public DetailedProcessDescriptor getProcessDetails(
			String url, String id) {
		return WPSHandler.instance().wpsGetDetailedProcessDescriptor(url, id);
	}

	@Override
	public WPSExecuteResponse wpsExecute(WPSExecuteRequest request) throws WPSConnectionException, WPSExecuteException, 
			RESTConnectionException, IOException, WCSConnectionException, WFSConnectionException {
		return WPSHandler.instance().wpsExecute(request);
	}

}