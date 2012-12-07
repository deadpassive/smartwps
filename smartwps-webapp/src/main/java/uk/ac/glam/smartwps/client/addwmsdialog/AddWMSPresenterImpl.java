package uk.ac.glam.smartwps.client.addwmsdialog;

import uk.ac.glam.smartwps.client.event.ShowWMSDialogEvent;
import uk.ac.glam.smartwps.client.event.ShowWMSDialogHandler;

import com.google.web.bindery.event.shared.EventBus;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class AddWMSPresenterImpl implements AddWMSPresenter {
	
	/**
	 * TODO: document
	 * @param eventBus
	 * @param display
	 */
	public AddWMSPresenterImpl(EventBus eventBus, final AddWMSPresenter.Display display) {
		
		eventBus.addHandler(ShowWMSDialogEvent.TYPE, new ShowWMSDialogHandler() {
			
			@Override
			public void onShowDialog(ShowWMSDialogEvent event) {
				display.showDialog();
			}
		});
	}
}
