package uk.ac.glam.smartwps.shared.wps.input;


/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class LiteralProcessInput extends ProcessInput {

	private String literalValue;
	
	/**
	 * TODO: document
	 */
	public LiteralProcessInput(){
		super(null);
	}
	
	/**
	 * TODO: document
	 * @param identifier
	 * @param literalValue
	 */
	public LiteralProcessInput(String identifier, String literalValue) {
		super(identifier);
		this.literalValue = literalValue;
	}

	/**
	 * @return the value of this input
	 */
	public String getLiteralValue() {
		return literalValue;
	}
	
	@Override
	public String toString() {
		return "id: " + id + ", value: " + literalValue;
	}

	@Override
	public String getId() {
		return id;
	}
}
