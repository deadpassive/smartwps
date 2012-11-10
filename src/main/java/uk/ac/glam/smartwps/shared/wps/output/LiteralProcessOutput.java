package uk.ac.glam.smartwps.shared.wps.output;


/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class LiteralProcessOutput extends ProcessOutput {

	private String value;
	
	
	/**
	 * Empty constructor for serialization.
	 */
	public LiteralProcessOutput(){}
	
	/**
	 * TODO: document
	 * @param identifier
	 * @param value
	 */
	public LiteralProcessOutput(String identifier, String value) {
		setIdentifier(identifier);
		this.value = value;
	}
	
	/**
	 * TODO: document
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public String getValue() {
		return value;
	}
}
