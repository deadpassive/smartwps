package uk.ac.glam.smartwps.wps.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @TODO: document
 * @author jonb
 *
 */
public interface ProcessResultsReceivedHandler extends EventHandler {
	
	/**
	 * TODO: document
	 * @param event
	 */
	void onResultsReceived(ProcessResultsReceivedEvent event);
}
