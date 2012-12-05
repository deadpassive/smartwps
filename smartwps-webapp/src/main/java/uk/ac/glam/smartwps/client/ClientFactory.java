package uk.ac.glam.smartwps.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import uk.ac.glam.smartwps.client.processresults.ProcessResultsView;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public interface ClientFactory {
	
	/**
	 * TODO: document
	 * @return
	 */
	EventBus getEventBus();
	
	/**
	 * TODO: document
	 * @return
	 */
	PlaceController getPlaceController();
	
	/**
	 * TODO: document
	 * @return
	 */
	ProcessResultsView getProcessResultsView();
}
