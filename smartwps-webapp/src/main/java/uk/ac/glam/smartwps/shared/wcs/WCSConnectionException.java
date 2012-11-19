package uk.ac.glam.smartwps.shared.wcs;

import uk.ac.glam.smartwps.client.SmartWPSException;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WCSConnectionException extends SmartWPSException {

	/**
	 * TODO: document
	 */
	public WCSConnectionException(){}
	
	/**
	 * TODO: document
	 * @param details
	 * @param originalStackTrace
	 */
	public WCSConnectionException(String details, String originalStackTrace) {
		super(details, originalStackTrace);
	}

	/**
	 * TODO: document
	 * @param details
	 */
	public WCSConnectionException(String details) {
		super(details);
	}
}
