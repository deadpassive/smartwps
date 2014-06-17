package uk.ac.glam.smartwps.base.client.event;

import java.util.Set;

import uk.ac.glam.smartwps.base.shared.Data;

import com.google.gwt.event.shared.GwtEvent;

/**
 * An event for adding layers.
 * @author jonb
 */
public class AddLayersEvent extends GwtEvent<AddLayersHandler> {

	@SuppressWarnings("javadoc")
	public final static Type<AddLayersHandler> TYPE = new Type<AddLayersHandler>();
	private final Set<? extends Data> layers;
	
	/**
	 * Construct a new AddLayersEvent.
	 * @param layers the layers to add
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
	 * Retrieve the layers to be added.
	 * @return
	 */
	public Set<? extends Data> getLayers() {
		return layers;
	}
}
