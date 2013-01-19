package uk.ac.glam.smartwps.wcs.shared.v100;

import java.io.Serializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class Service implements Serializable {

	private String description;

	/**
	 * TODO: document
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getDescription() {
		return description;
	}

}
