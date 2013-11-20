package uk.ac.glam.smartwps.base.client.mvp;

import uk.ac.glam.smartwps.base.client.event.PlaceRequestEvent;
import uk.ac.glam.smartwps.base.client.event.PlaceRequestEventHandler;

import com.google.web.bindery.event.shared.EventBus;

public class PresenterBase {

	protected final EventBus eventBus;

	public PresenterBase(EventBus eventBus, final String placeName) {
		this.eventBus = eventBus;
		
		eventBus.addHandler(PlaceRequestEvent.TYPE, new PlaceRequestEventHandler() {
			
			@Override
			public void onPlaceRequest(PlaceRequestEvent request) {
				if (request.getPlaceName().equals(placeName)) {
					// TODO: show the view - what should I do if it's NOT this presenter?
				}
			}
		});
	}
}
