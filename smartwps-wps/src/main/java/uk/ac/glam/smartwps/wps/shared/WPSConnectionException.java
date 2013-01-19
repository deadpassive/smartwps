package uk.ac.glam.smartwps.wps.shared;

/**
 * Indicates that an error has occurred while trying to connect to a WPS.
 * 
 * @author Jon Britton
 */
public class WPSConnectionException extends Exception {

	/**
	 * Create a new exception.
	 */
	public WPSConnectionException(){}
	
	/**
	 * Create a new exception with a detail message.
	 * @param detail the exception details
	 */
	public WPSConnectionException(String detail) {
		super(detail);
	}
}
