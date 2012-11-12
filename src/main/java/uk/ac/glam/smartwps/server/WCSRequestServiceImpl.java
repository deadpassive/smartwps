package uk.ac.glam.smartwps.server;

import java.io.IOException;

import uk.ac.glam.smartwps.client.RESTConnectionException;
import uk.ac.glam.smartwps.client.net.WCSRequestService;
import uk.ac.glam.smartwps.client.wcs.WCSConnectionException;
import uk.ac.glam.smartwps.client.wms.WMSConnectionException;
import uk.ac.glam.smartwps.server.wcs.WCSHandler;
import uk.ac.glam.smartwps.shared.request.WCSDescribeCoverageRequest;
import uk.ac.glam.smartwps.shared.request.WCSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.request.WCSGetCoverageAndStoreRequest;
import uk.ac.glam.smartwps.shared.response.WCSCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.response.WCSDescribeCoverageResponse;
import uk.ac.glam.smartwps.shared.response.WCSGetCoverageAndStoreResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class WCSRequestServiceImpl extends RemoteServiceServlet implements WCSRequestService {
	
	public WCSCapabilitiesResponse wcsGetCapabilities(WCSGetCapabilitiesRequest request) 
            throws WCSConnectionException {
		return WCSHandler.instance().wcsGetCapabilities(request.getServiceUrl());
	}

	public WCSDescribeCoverageResponse wcsDescribeCoverage(WCSDescribeCoverageRequest request) 
            throws WCSConnectionException {
		return WCSHandler.instance().wcsDescribeCoverage(request.getServiceUrl(), request.getCoverageID(), 
                request.isReloadCaps());
	}

	/**
	 * Carries out a WCS 1.1.1 GetCoverage request and stores the resulting coverage locally.
	 */
	public WCSGetCoverageAndStoreResponse wcsGetCoverageAndStore(WCSGetCoverageAndStoreRequest request) 
            throws IOException, WMSConnectionException, RESTConnectionException, WCSConnectionException {
		return WCSHandler.instance().wcsGetCoverageAndStore(request);
	}	
	
}
