package uk.ac.glam.smartwps.client.event;

import java.util.Set;

import uk.ac.glam.smartwps.shared.Data;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @TODO: document
 * @author jonb
 */
public class AddLayersEvent extends GwtEvent<AddLayerHandler> {

	@SuppressWarnings("javadoc")
	public final static Type<AddLayerHandler> TYPE = new Type<AddLayerHandler>();
	private final Set<? extends Data> layers;
	
	/**
	 * TODO: document
	 * @param layers
	 */
	public AddLayersEvent(Set<? extends Data> layers) {
		this.layers = layers;
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
	public Set<? extends Data> getLayers() {
		return layers;
	}
}
