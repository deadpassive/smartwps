package uk.ac.glam.smartwps.client.addwmsdialog;

import java.util.List;

import uk.ac.glam.smartwps.shared.wms.WMSLayer;

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

	private AddWMSPresenter presenter;

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
		center();
	}
	
	@Override
	public void setPresenter(AddWMSPresenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void setWMSLayers(List<WMSLayer> wmsLayers) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFailure(String message) {
		// TODO Auto-generated method stub
		
	}
}
