package uk.ac.glam.smartwps.client.event;

import uk.ac.glam.smartwps.wps.shared.WPSExecuteResponse;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @TODO: document
 * 
 * @author jonb
 */
public class ProcessResultsReceivedEvent extends GwtEvent<ProcessResultsReceivedHandler> {

	@SuppressWarnings("javadoc")
	public final static Type<ProcessResultsReceivedHandler> TYPE = new Type<ProcessResultsReceivedHandler>();
	
	private WPSExecuteResponse results;
	
	/**
	 * TODO: document
	 * @param results
	 */
	public ProcessResultsReceivedEvent(WPSExecuteResponse results) {
		this.results = results;
	}
	
	@Override
	public Type<ProcessResultsReceivedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ProcessResultsReceivedHandler handler) {
		handler.onResultsReceived(this);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public WPSExecuteResponse getResults() {
		return results;
	}

}
