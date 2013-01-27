package uk.ac.glam.smartwps.base.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @TODO: document
 * @author jonb
 */
public class AddProcessEvent extends GwtEvent<AddProcessHandler> {

	@SuppressWarnings("javadoc")
	public final static Type<AddProcessHandler> TYPE = new Type<AddProcessHandler>();
	private final ListGridRecord processRecord;
	
	/**
	 * TODO: document
	 * @param processRecord 
	 */
	public AddProcessEvent(ListGridRecord processRecord) {
		this.processRecord = processRecord;
	}
	
	@Override
	public Type<AddProcessHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddProcessHandler handler) {
		handler.onAddProcess(this);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public ListGridRecord getProcessRecord() {
		return processRecord;
	}
}
