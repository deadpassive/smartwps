package uk.ac.glam.smartwps.wps.shared.output;


/**
 * A process output containing a single literal value.
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
	 * Create a new literal process output with the given ID and value.
	 * @param identifier the output identifier
	 * @param value the output value
	 */
	public LiteralProcessOutput(String identifier, String value) {
		setIdentifier(identifier);
		this.value = value;
	}
	
	/**
	 * @return the value of this literal output
	 */
	public String getValue() {
		return value;
	}
}
