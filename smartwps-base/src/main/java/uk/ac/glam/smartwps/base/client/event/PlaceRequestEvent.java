package uk.ac.glam.smartwps.base.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class PlaceRequestEvent extends GwtEvent<PlaceRequestEventHandler> {

	public final static Type<PlaceRequestEventHandler> TYPE = new Type<PlaceRequestEventHandler>();
	private String placeName;
	
	public PlaceRequestEvent(String placeName) {
		this.placeName = placeName;
	}

	@Override
	public Type<PlaceRequestEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PlaceRequestEventHandler handler) {
		handler.onPlaceRequest(this);
	}
	
	public String getPlaceName() {
		return placeName;
	}
}
