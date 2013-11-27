package uk.ac.glam.smartwps.base.client.mvp;

import uk.ac.glam.smartwps.base.client.event.PlaceRequestEvent;
import uk.ac.glam.smartwps.base.client.event.PlaceRequestEventHandler;

import com.google.web.bindery.event.shared.EventBus;

public class PresenterBase<ViewType extends View> {

	protected final EventBus eventBus;
	protected final ViewType view;

	public PresenterBase(EventBus eventBus, final ViewType view, final String placeName) {
		this.eventBus = eventBus;
		this.view = view;
		
		eventBus.addHandler(PlaceRequestEvent.TYPE, new PlaceRequestEventHandler() {
			
			@Override
			public void onPlaceRequest(PlaceRequestEvent request) {
				if (request.getPlaceName().equals(placeName)) {
					showView();
				}
			}
		});
	}
	
	protected void showView() {
		view.showView();
	}
}
