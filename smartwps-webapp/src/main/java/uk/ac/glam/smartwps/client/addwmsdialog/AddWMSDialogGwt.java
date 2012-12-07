package uk.ac.glam.smartwps.client.addwmsdialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class AddWMSDialogGwt extends DialogBox implements AddWMSPresenter.Display {

	private static AddWMSDialogGwtUiBinder uiBinder = GWT.create(AddWMSDialogGwtUiBinder.class);

	/**
	 * TODO: document
	 */
	interface AddWMSDialogGwtUiBinder extends UiBinder<Widget, AddWMSDialogGwt> {}

	/**
	 * TODO: document
	 */
	public AddWMSDialogGwt() {
		setText("Add WMS Layer2");
		setWidget(uiBinder.createAndBindUi(this));
		getElement().getStyle().setZIndex(1000000);
//		setSize("500px", "500px");
	}

	@Override
	public void showDialog() {
		// TODO Auto-generated method stub
		center();
	}
}
