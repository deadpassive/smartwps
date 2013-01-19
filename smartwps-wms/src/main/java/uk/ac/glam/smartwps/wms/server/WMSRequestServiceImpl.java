package uk.ac.glam.smartwps.wms.server;

import java.util.logging.Logger;

import uk.ac.glam.smartwps.wms.client.net.WMSRequestService;
import uk.ac.glam.smartwps.wms.shared.WMSConnectionException;
import uk.ac.glam.smartwps.wms.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wms.shared.response.WMSGetCapabilitiesResponse;

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
