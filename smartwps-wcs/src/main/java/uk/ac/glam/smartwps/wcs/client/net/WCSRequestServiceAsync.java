package uk.ac.glam.smartwps.wcs.client.net;

import uk.ac.glam.smartwps.wcs.shared.WCSCapabilitiesResponse;
import uk.ac.glam.smartwps.wcs.shared.WCSDescribeCoverageRequest;
import uk.ac.glam.smartwps.wcs.shared.WCSDescribeCoverageResponse;
import uk.ac.glam.smartwps.wcs.shared.WCSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wcs.shared.WCSGetCoverageAndStoreRequest;
import uk.ac.glam.smartwps.wcs.shared.WCSGetCoverageAndStoreResponse;

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
