package uk.ac.glam.smartwps.wps.server;

import java.io.IOException;

import uk.ac.glam.smartwps.base.shared.RESTConnectionException;
import uk.ac.glam.smartwps.wcs.shared.WCSConnectionException;
import uk.ac.glam.smartwps.wfs.shared.WFSConnectionException;
import uk.ac.glam.smartwps.wps.client.net.WPSRequestService;
import uk.ac.glam.smartwps.wps.shared.DetailedProcessDescriptor;
import uk.ac.glam.smartwps.wps.shared.WPSConnectionException;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteException;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteRequest;
import uk.ac.glam.smartwps.wps.shared.WPSExecuteResponse;
import uk.ac.glam.smartwps.wps.shared.WPSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wps.shared.WPSGetCapabilitiesResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Contains a number of GWT RPC methods for accessing Open Web Services.
 * 
 * @author Jon
 */
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