package uk.ac.glam.smartwps.client.event;

import uk.ac.glam.smartwps.shared.Data;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @TODO: document
 * @author jonb
 */
public class AddLayerEvent extends GwtEvent<AddLayerHandler> {

	@SuppressWarnings("javadoc")
	public final static Type<AddLayerHandler> TYPE = new Type<AddLayerHandler>();
	private final Data layer;
	
	/**
	 * TODO: document
	 * @param layer
	 */
	public AddLayerEvent(Data layer) {
		this.layer = layer;
	}
	
	@Override
	public Type<AddLayerHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddLayerHandler handler) {
		handler.onAddLayer(this);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public Data getLayer() {
		return layer;
	}
}
