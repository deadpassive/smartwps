package uk.ac.glam.smartwps.shared.wps.input;

import java.io.Serializable;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public interface ProcessInput extends Serializable {
	
	/**
	 * TODO: document
	 * @param identifier
	 */
	public void setId(String identifier);
	
	/**
	 * @return the process input identifier
	 */
	public String getId();	
}
