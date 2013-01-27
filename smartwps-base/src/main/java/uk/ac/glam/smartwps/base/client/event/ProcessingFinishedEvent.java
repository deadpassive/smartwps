package uk.ac.glam.smartwps.base.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @TODO: document
 * @author jonb
 */
public class ProcessingFinishedEvent extends GwtEvent<ProcessingFinishedHandler> {

	@SuppressWarnings("javadoc")
	public final static Type<ProcessingFinishedHandler> TYPE = new Type<ProcessingFinishedHandler>();

	@Override
	public Type<ProcessingFinishedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ProcessingFinishedHandler handler) {
		handler.onProcessingFinished(this);
	}
}
