package uk.ac.glam.smartwps.shared.wms;


/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class WMSConnectionException extends Exception {

	/**
	 * TODO: document
	 */
	public WMSConnectionException(){}
	
	/**
	 * TODO: document
	 * @param detail
	 */
	public WMSConnectionException(String detail) {
		super(detail);
	}
}
