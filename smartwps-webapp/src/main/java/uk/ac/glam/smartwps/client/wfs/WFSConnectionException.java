package uk.ac.glam.smartwps.client.wfs;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class WFSConnectionException extends Exception {
	
	/**
	 * TODO: document
	 */
	public WFSConnectionException(){}
	
	/**
	 * TODO: document
	 * @param details
	 */
	public WFSConnectionException(String details) {
		super(details);
	}
}
