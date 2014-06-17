package uk.ac.glam.smartwps.base.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for the AddLayersEvent.
 * @author jonb
 *
 */
public interface AddLayersHandler extends EventHandler {
	
	/**
	 * Called for AddLayerEvents.
	 * @param event the event
	 */
	void onAddLayer(AddLayersEvent event);
}
