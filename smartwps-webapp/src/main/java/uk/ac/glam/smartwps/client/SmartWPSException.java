package uk.ac.glam.smartwps.client;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class SmartWPSException extends Exception {

	private String originalStackTrace;
	
	/**
	 * TODO: document
	 */
	public SmartWPSException(){}

	/**
	 * TODO: document
	 * @param details
	 * @param originalStackTrace
	 */
	public SmartWPSException(String details, String originalStackTrace) {
		super(details);
		this.setOriginalStackTrace(originalStackTrace);
	}

	/**
	 * TODO: document
	 * @param details
	 */
	public SmartWPSException(String details) {
		super(details);
	}

	/**
	 * TODO: document
	 * @param originalStackTrace
	 */
	public final void setOriginalStackTrace(String originalStackTrace) {
		this.originalStackTrace = originalStackTrace;
	}

	/**
	 * TODO: document
	 * @return the original stack trace string
	 */
	public String getOriginalStackTrace() {
		return originalStackTrace;
	}
}
