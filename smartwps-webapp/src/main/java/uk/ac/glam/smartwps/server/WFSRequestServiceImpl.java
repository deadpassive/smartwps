package uk.ac.glam.smartwps.server;

import uk.ac.glam.smartwps.wfs.client.net.WFSRequestService;
import uk.ac.glam.smartwps.wfs.server.WFSHandler;
import uk.ac.glam.smartwps.wfs.shared.WFSConnectionException;
import uk.ac.glam.smartwps.wfs.shared.WFSDescribeFeatureTypeRequest;
import uk.ac.glam.smartwps.wfs.shared.WFSDescribeFeatureTypeResponse;
import uk.ac.glam.smartwps.wfs.shared.WFSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wfs.shared.WFSGetCapabilitiesResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Contains a number of GWT RPC methods for accessing Open Web Services.
 * 
 * @author Jon
 * 
 */
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