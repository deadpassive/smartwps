package uk.ac.glam.smartwps.base.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @TODO: document
 * @author jonb
 *
 */
public interface ProcessingFinishedHandler extends EventHandler {
	
	/**
	 * TODO: document
	 * @param event
	 */
	void onProcessingFinished(ProcessingFinishedEvent event);
}
