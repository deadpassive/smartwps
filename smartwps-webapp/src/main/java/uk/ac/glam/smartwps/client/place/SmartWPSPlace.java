package uk.ac.glam.smartwps.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class SmartWPSPlace extends Place {

	/**
	 * @TODO: document
	 * @author jonb
	 *
	 */
	public static class Tokenizer implements PlaceTokenizer<SmartWPSPlace> {

		@Override
		public String getToken(SmartWPSPlace place) {
			return null;
		}

		@Override
		public SmartWPSPlace getPlace(String token) {
			return new SmartWPSPlace();
		}
	}
}
