package uk.ac.glam.smartwps.server;

import uk.ac.glam.smartwps.client.net.WFSRequestService;
import uk.ac.glam.smartwps.client.wfs.WFSConnectionException;
import uk.ac.glam.smartwps.server.wfs.WFSHandler;
import uk.ac.glam.smartwps.shared.request.WFSDescribeFeatureTypeRequest;
import uk.ac.glam.smartwps.shared.request.WFSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WFSDescribeFeatureTypeResponse;
import uk.ac.glam.smartwps.shared.response.WFSGetCapabilitiesResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Contains a number of GWT RPC methods for accessing Open Web Services.
 * 
 * @author Jon
 * 
 */
@SuppressWarnings("serial")
public class WFSRequestServiceImpl extends RemoteServiceServlet implements
		WFSRequestService {
		
	@Override
	public WFSGetCapabilitiesResponse wfsGetCapabilities(WFSGetCapabilitiesRequest request) throws WFSConnectionException {
		return WFSHandler.instance().wfsGetCapabilities(request);
	}

	@Override
	public WFSDescribeFeatureTypeResponse wfsDescribeFeatureType(WFSDescribeFeatureTypeRequest request) throws WFSConnectionException {
		return WFSHandler.instance().wfsDescribeFeatureType(request);
	}


}