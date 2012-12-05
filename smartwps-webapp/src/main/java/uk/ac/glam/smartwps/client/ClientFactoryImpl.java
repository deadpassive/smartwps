package uk.ac.glam.smartwps.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import uk.ac.glam.smartwps.client.processresults.ProcessResultsView;
import uk.ac.glam.smartwps.client.processresults.ProcessResultsViewImpl;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class ClientFactoryImpl implements ClientFactory {
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	private static final ProcessResultsView processResultsView = new ProcessResultsViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public ProcessResultsView getProcessResultsView() {
		return processResultsView;
	}
}
