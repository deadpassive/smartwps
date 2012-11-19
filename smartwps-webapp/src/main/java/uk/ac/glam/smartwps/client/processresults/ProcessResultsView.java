package uk.ac.glam.smartwps.client.processresults;

import uk.ac.glam.smartwps.shared.response.WPSExecuteResponse;

import com.google.gwt.user.client.ui.IsWidget;

public interface ProcessResultsView  extends IsWidget  {
	/**
	 * TODO: document
	 */
	public static final String IDENTIFIER = "identifier";
	/**
	 * TODO: document
	 */
	public static final String TITLE = "title";
	/**
	 * TODO: document
	 */
	public static final String MIME_TYPE = "mimeType";
	/**
	 * TODO: document
	 */
	public static final String VALUE = "value";
	/**
	 * TODO: document
	 */
	public static final String ACTION = "action";
	
	/**
	 * TODO: Move this functionality over to the presenter.
	 * TODO: document
	 * @param result
	 */
	public void addProcessResultsTab(WPSExecuteResponse result);
}
