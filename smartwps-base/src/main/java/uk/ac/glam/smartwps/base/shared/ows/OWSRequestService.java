package uk.ac.glam.smartwps.base.shared.ows;

import uk.ac.glam.smartwps.base.shared.response.RetrieveServerLogsResponse;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * TODO: document
 * @author Jon Britton
 */
@RemoteServiceRelativePath("ows")
public interface OWSRequestService extends RemoteService {

	/**
	 * @return logs from the server
	 */
	RetrieveServerLogsResponse retrieveServerLogs();
	
	/**
	 * TODO: document
	 * @param moduleBaseURL
	 */
	void setModuleBaseURL(String moduleBaseURL);

	/**
	 * Temporary test method.
	 */
	void testSomething();
}
