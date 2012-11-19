package uk.ac.glam.smartwps.client.processresults;

import uk.ac.glam.smartwps.client.ClientFactory;
import uk.ac.glam.smartwps.client.event.ProcessResultsReceivedEvent;
import uk.ac.glam.smartwps.client.event.ProcessResultsReceivedHandler;
import uk.ac.glam.smartwps.client.place.SmartWPSPlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;

public class ProcessResultsActivity extends AbstractActivity {
	private EventBus eventBus;
	private ClientFactory clientFactory;
	
	public ProcessResultsActivity(SmartWPSPlace mainAppPlace, ClientFactory factory) {
		this.clientFactory = factory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
		final ProcessResultsView view = clientFactory.getProcessResultsView();
		
		this.eventBus = eventBus;
		
		eventBus.addHandler(ProcessResultsReceivedEvent.TYPE, new ProcessResultsReceivedHandler() {
			
			@Override
			public void onResultsReceived(ProcessResultsReceivedEvent event) {
				view.addProcessResultsTab(event.getResults());
			}
		});
		
		panel.setWidget(clientFactory.getProcessResultsView());
	}
}
