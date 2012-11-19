package uk.ac.glam.smartwps.client.wps;

/**
 * Represents an error that occurred during a WPS Execute request.
 * 
 * @author Jon Britton
 */
public class WPSExecuteException extends Exception {

	/**
	 * Create a new exception with no message.
	 */
	public WPSExecuteException(){}
	
	/**
	 * Create a new exception with the given detail message.
	 * @param details
	 */
	public WPSExecuteException(String details) {
		super(details);
	}
}
