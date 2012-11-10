package uk.ac.glam.smartwps.client.net;

import uk.ac.glam.smartwps.shared.response.RetrieveServerLogsResponse;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO: document
 * @author Jon Britton
 */
public interface OWSRequestServiceAsync {
	/**
	 * TODO: document
	 * @param callback
	 */
	void retrieveServerLogs(AsyncCallback<RetrieveServerLogsResponse> callback);
	/**
	 * TODO: document
	 * @param callback
	 */
	void testSomething(@SuppressWarnings("rawtypes") AsyncCallback callback);
	/**
	 * TODO: document
	 * @param url
	 * @param callback
	 */
	void setModuleBaseURL(String url, @SuppressWarnings("rawtypes") AsyncCallback callback);
}
