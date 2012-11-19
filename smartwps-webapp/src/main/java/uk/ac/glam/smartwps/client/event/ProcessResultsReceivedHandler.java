package uk.ac.glam.smartwps.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ProcessResultsReceivedHandler extends EventHandler {
	void onResultsReceived(ProcessResultsReceivedEvent event);
}
