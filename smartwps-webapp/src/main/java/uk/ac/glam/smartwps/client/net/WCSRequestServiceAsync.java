package uk.ac.glam.smartwps.client.net;

import uk.ac.glam.smartwps.shared.request.WCSDescribeCoverageRequest;
import uk.ac.glam.smartwps.shared.request.WCSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.request.WCSGetCoverageAndStoreRequest;
import uk.ac.glam.smartwps.shared.response.WCSCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.response.WCSDescribeCoverageResponse;
import uk.ac.glam.smartwps.shared.response.WCSGetCoverageAndStoreResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public interface WCSRequestServiceAsync {
	
	/**
	 * TODO: document
	 * @param request
	 * @param callback
	 */
	void wcsGetCapabilities(WCSGetCapabilitiesRequest request, AsyncCallback<WCSCapabilitiesResponse> callback);
	
	/**
	 * TODO: document
	 * @param requests
	 * @param callback
	 */
	void wcsDescribeCoverage(WCSDescribeCoverageRequest requests, AsyncCallback<WCSDescribeCoverageResponse> callback);
	
	/**
	 * TODO: document
	 * @param request
	 * @param callback
	 */
	void wcsGetCoverageAndStore(WCSGetCoverageAndStoreRequest request, 
            AsyncCallback<WCSGetCoverageAndStoreResponse> callback);
}
