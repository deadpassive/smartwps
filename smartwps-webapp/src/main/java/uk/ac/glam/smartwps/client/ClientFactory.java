package uk.ac.glam.smartwps.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import uk.ac.glam.smartwps.client.processresults.ProcessResultsView;

public interface ClientFactory {
	EventBus getEventBus();
	PlaceController getPlaceController();
	ProcessResultsView getProcessResultsView();
}
