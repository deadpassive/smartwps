package uk.ac.glam.smartwps.client.net;

import uk.ac.glam.smartwps.client.wfs.WFSConnectionException;
import uk.ac.glam.smartwps.shared.request.WFSDescribeFeatureTypeRequest;
import uk.ac.glam.smartwps.shared.request.WFSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WFSDescribeFeatureTypeResponse;
import uk.ac.glam.smartwps.shared.response.WFSGetCapabilitiesResponse;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * TODO: document
 * @author Jon Britton
 * 
 */
@RemoteServiceRelativePath("wfs")
public interface WFSRequestService extends RemoteService {

	/**
	 * TODO: document
	 * @param request
	 * @return the WFS capabilities metadata
	 * @throws WFSConnectionException
	 */
	WFSGetCapabilitiesResponse wfsGetCapabilities(WFSGetCapabilitiesRequest request) throws WFSConnectionException;

	/**
	 * TODO: document
	 * @param request
	 * @return the WFS feature type metadata
	 * @throws WFSConnectionException
	 */
	WFSDescribeFeatureTypeResponse wfsDescribeFeatureType(WFSDescribeFeatureTypeRequest request) 
            throws WFSConnectionException;

}
