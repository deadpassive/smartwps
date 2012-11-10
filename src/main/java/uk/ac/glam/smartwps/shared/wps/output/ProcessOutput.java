package uk.ac.glam.smartwps.shared.wps.output;

import java.io.Serializable;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public abstract class ProcessOutput implements Serializable {
	private String identifier;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
