package uk.ac.glam.smartwps.client.event;

import uk.ac.glam.smartwps.shared.response.WPSExecuteResponse;

import com.google.gwt.event.shared.GwtEvent;

public class ProcessResultsReceivedEvent extends GwtEvent<ProcessResultsReceivedHandler> {

	public static Type<ProcessResultsReceivedHandler> TYPE = new Type<ProcessResultsReceivedHandler>();
	private WPSExecuteResponse results;
	
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

	public WPSExecuteResponse getResults() {
		return results;
	}

}
