package uk.ac.glam.smartwps.client.loggerdialog;

import uk.ac.glam.smartwps.client.event.ShowLoggerDialogEvent;
import uk.ac.glam.smartwps.client.event.ShowLoggerDialogHandler;
import com.google.web.bindery.event.shared.EventBus;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class LoggerPresenterImpl implements LoggerPresenter {
	
	/**
	 * TODO: document
	 * @param eventBus
	 * @param display
	 */
	public LoggerPresenterImpl(EventBus eventBus, final LoggerPresenter.Display display) {
		eventBus.addHandler(ShowLoggerDialogEvent.TYPE, new ShowLoggerDialogHandler() {
			
			@Override
			public void onShowDialog(ShowLoggerDialogEvent event) {
				display.showDialog();
			}
		});
	}
}

