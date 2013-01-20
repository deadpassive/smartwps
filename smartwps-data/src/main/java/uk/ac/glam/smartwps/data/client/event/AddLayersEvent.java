package uk.ac.glam.smartwps.data.client.event;

import java.util.Set;

import uk.ac.glam.smartwps.data.shared.Data;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @TODO: document
 * @author jonb
 */
public class AddLayersEvent extends GwtEvent<AddLayersHandler> {

	@SuppressWarnings("javadoc")
	public final static Type<AddLayersHandler> TYPE = new Type<AddLayersHandler>();
	private final Set<? extends Data> layers;
	
	/**
	 * TODO: document
	 * @param layers
	 */
	public AddLayersEvent(Set<? extends Data> layers) {
		this.layers = layers;
	}
	
	@Override
	public Type<AddLayersHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddLayersHandler handler) {
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
