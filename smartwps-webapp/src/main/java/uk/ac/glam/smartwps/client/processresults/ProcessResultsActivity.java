package uk.ac.glam.smartwps.client.processresults;

import uk.ac.glam.smartwps.client.ClientFactory;
import uk.ac.glam.smartwps.client.place.SmartWPSPlace;
import uk.ac.glam.smartwps.wps.client.event.ProcessResultsReceivedEvent;
import uk.ac.glam.smartwps.wps.client.event.ProcessResultsReceivedHandler;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class ProcessResultsActivity extends AbstractActivity {
	private ClientFactory clientFactory;
	
	/**
	 * TODO: document
	 * @param mainAppPlace
	 * @param factory
	 */
	public ProcessResultsActivity(SmartWPSPlace mainAppPlace, ClientFactory factory) {
		this.clientFactory = factory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final ProcessResultsView view = clientFactory.getProcessResultsView();
				
		eventBus.addHandler(ProcessResultsReceivedEvent.TYPE, new ProcessResultsReceivedHandler() {
			
			@Override
			public void onResultsReceived(ProcessResultsReceivedEvent event) {
				view.addProcessResultsTab(event.getResults());
			}
		});
		
		panel.setWidget(clientFactory.getProcessResultsView());
	}
}
