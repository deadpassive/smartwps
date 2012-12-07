package uk.ac.glam.smartwps.client.processresults;

import uk.ac.glam.smartwps.client.ClientFactory;
import uk.ac.glam.smartwps.client.place.SmartWPSPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class ProcessResultsActivityMapper implements ActivityMapper {
	private ClientFactory clientFactory;
	
	/**
	 * TODO: document
	 * @param clientFactory
	 */
	public ProcessResultsActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof SmartWPSPlace) {
			return new ProcessResultsActivity((SmartWPSPlace) place, clientFactory);
		}
		return null;
	}
}
