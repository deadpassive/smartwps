package uk.ac.glam.smartwps.client.addwmsdialog;

import com.google.web.bindery.event.shared.EventBus;

public class AddWMSPresenterImpl implements AddWMSPresenter {
	
	private final EventBus eventBus;
	private final Display display;

	public AddWMSPresenterImpl(EventBus eventBus, AddWMSPresenter.Display display) {
		this.eventBus = eventBus;
		this.display = display;
	}
}
