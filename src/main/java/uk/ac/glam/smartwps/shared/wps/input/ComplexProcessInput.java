package uk.ac.glam.smartwps.shared.wps.input;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public abstract class ComplexProcessInput implements ProcessInput {

	/**
	 * TODO: document
	 */
	String id;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String identifier) {
		this.id = identifier;
	}
}
