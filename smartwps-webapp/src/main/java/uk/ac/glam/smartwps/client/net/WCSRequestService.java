package uk.ac.glam.smartwps.client.net;

import java.io.IOException;

import uk.ac.glam.smartwps.client.RESTConnectionException;
import uk.ac.glam.smartwps.shared.request.WCSDescribeCoverageRequest;
import uk.ac.glam.smartwps.shared.request.WCSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.request.WCSGetCoverageAndStoreRequest;
import uk.ac.glam.smartwps.shared.response.WCSCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.response.WCSDescribeCoverageResponse;
import uk.ac.glam.smartwps.shared.response.WCSGetCoverageAndStoreResponse;
import uk.ac.glam.smartwps.wcs.shared.WCSConnectionException;
import uk.ac.glam.smartwps.wms.shared.WMSConnectionException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@RemoteServiceRelativePath("wcs")
public interface WCSRequestService extends RemoteService {
	

	/**
	 * TODO: document
	 * @param request
	 * @return
	 * @throws WCSConnectionException
	 */
	WCSCapabilitiesResponse wcsGetCapabilities(WCSGetCapabilitiesRequest request)
			throws WCSConnectionException;

	/**
	 * TODO: document
	 * @param request
	 * @return
	 * @throws WCSConnectionException
	 */
	WCSDescribeCoverageResponse wcsDescribeCoverage(WCSDescribeCoverageRequest request)
			throws WCSConnectionException;

	/**
	 * TODO: document
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws WMSConnectionException
	 * @throws RESTConnectionException
	 * @throws WCSConnectionException
	 */
	WCSGetCoverageAndStoreResponse wcsGetCoverageAndStore(WCSGetCoverageAndStoreRequest request) 
            throws IOException, WMSConnectionException, RESTConnectionException, WCSConnectionException;

}
