package uk.ac.glam.smartwps.wms.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @TODO: document
 * @author jonb
 */
public class ShowWMSDialogEvent extends GwtEvent<ShowWMSDialogHandler> {

	public final static Type<ShowWMSDialogHandler> TYPE = new Type<ShowWMSDialogHandler>();
	
	@Override
	public Type<ShowWMSDialogHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowWMSDialogHandler handler) {
		handler.onShowDialog(this);
	}
}
