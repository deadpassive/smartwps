package uk.ac.glam.smartwps.wfs.client.net;

import uk.ac.glam.smartwps.wfs.shared.WFSConnectionException;
import uk.ac.glam.smartwps.wfs.shared.WFSDescribeFeatureTypeRequest;
import uk.ac.glam.smartwps.wfs.shared.WFSDescribeFeatureTypeResponse;
import uk.ac.glam.smartwps.wfs.shared.WFSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wfs.shared.WFSGetCapabilitiesResponse;

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
	 * @return
	 * @throws WFSConnectionException
	 */
	WFSGetCapabilitiesResponse wfsGetCapabilities(WFSGetCapabilitiesRequest request) throws WFSConnectionException;

	/**
	 * TODO: document
	 * @param request
	 * @return
	 * @throws WFSConnectionException
	 */
	WFSDescribeFeatureTypeResponse wfsDescribeFeatureType(WFSDescribeFeatureTypeRequest request) 
            throws WFSConnectionException;

}
