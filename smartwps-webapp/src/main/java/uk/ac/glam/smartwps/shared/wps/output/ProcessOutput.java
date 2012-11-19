package uk.ac.glam.smartwps.shared.wps.output;

import java.io.Serializable;

/**
 * Base class for all WPS process outputs.
 * 
 * @author Jon Britton
 */
public abstract class ProcessOutput implements Serializable {
	private String identifier;

	/**
	 * @return the process output identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Set the ID of this process output.
	 * @param identifier the output ID
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
