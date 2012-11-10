package uk.ac.glam.smartwps.client.wfs;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WFSConnectionException extends Exception {

	private static final long serialVersionUID = -932705993132311138L;
	
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
