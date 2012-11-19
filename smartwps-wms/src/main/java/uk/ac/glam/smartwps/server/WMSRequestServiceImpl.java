package uk.ac.glam.smartwps.server;

import java.util.logging.Logger;

import uk.ac.glam.smartwps.client.net.WMSRequestService;
import uk.ac.glam.smartwps.server.wms.WMSHandler;
import uk.ac.glam.smartwps.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WMSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wms.WMSConnectionException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class WMSRequestServiceImpl extends RemoteServiceServlet implements WMSRequestService {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");

	@Override
	public WMSGetCapabilitiesResponse wmsGetCapabilities(
			WMSGetCapabilitiesRequest request) throws WMSConnectionException {
		LOGGER.info("Handling wmsGetCapabilities request");
		return WMSHandler.instance().wmsGetCapabilities(request.getServiceUrl(), request.getLayers(), 
                request.isExactMatches());
	}
	
}
