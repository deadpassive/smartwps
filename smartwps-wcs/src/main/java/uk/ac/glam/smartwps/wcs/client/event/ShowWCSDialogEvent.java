package uk.ac.glam.smartwps.wcs.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @TODO: document
 * @author jonb
 */
public class ShowWCSDialogEvent extends GwtEvent<ShowWCSDialogHandler> {

	@SuppressWarnings("javadoc")
	public final static Type<ShowWCSDialogHandler> TYPE = new Type<ShowWCSDialogHandler>();
	
	@Override
	public Type<ShowWCSDialogHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowWCSDialogHandler handler) {
		handler.onShowDialog(this);
	}
}
