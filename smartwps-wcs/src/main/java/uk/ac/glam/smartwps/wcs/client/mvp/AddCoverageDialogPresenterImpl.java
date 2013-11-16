package uk.ac.glam.smartwps.wcs.client.mvp;

import com.google.web.bindery.event.shared.EventBus;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class AddCoverageDialogPresenterImpl implements AddCoverageDialogPresenter {
	
	private EventBus eventBus;
	private Display display;

	/**
	 * TODO: document
	 * @param eventBus
	 * @param display
	 */
	public AddCoverageDialogPresenterImpl(EventBus eventBus, AddCoverageDialogPresenter.Display display) {
		this.eventBus = eventBus;
		this.display = display;
	}

}
