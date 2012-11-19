package uk.ac.glam.smartwps.client.wps;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class WPSExecuteException extends Exception {

	/**
	 * TODO: document
	 */
	public WPSExecuteException(){}
	
	/**
	 * TODO: document
	 * @param details
	 */
	public WPSExecuteException(String details) {
		super(details);
	}
}
