package uk.ac.glam.smartwps.client.wps;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WPSExecuteException extends Exception {

	private static final long serialVersionUID = -6005311103413176270L;

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
