package uk.ac.glam.smartwps.base.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @TODO: document
 * @author jonb
 */
public class ShowLoggerDialogEvent extends GwtEvent<ShowLoggerDialogHandler> {

	@SuppressWarnings("javadoc")
	public final static Type<ShowLoggerDialogHandler> TYPE = new Type<ShowLoggerDialogHandler>();
	
	@Override
	public Type<ShowLoggerDialogHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowLoggerDialogHandler handler) {
		handler.onShowDialog(this);
	}
}
