package uk.ac.glam.smartwps.wps.shared.input;

import java.io.Serializable;

/**
 * Interface for a WPS process input.
 * 
 * @author Jon Britton
 */
public abstract class ProcessInput implements Serializable {
	
	/**
	 * TODO: document
	 */
	protected String id;
	
	/**
	 * TODO: document
	 * @param id
	 */
	public ProcessInput(String id) {
		this.id = id;
	}
	
//	/**
//	 * Set the identifier for this input.
//	 * @param identifier the input ID
//	 */
//	public void setId(String identifier) {
//		this.id = identifier;
//	}
	
	/**
	 * @return the process input identifier
	 */
	public String getId() {
		return id;
	}
}
