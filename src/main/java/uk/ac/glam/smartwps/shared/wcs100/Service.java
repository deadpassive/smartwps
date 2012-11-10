package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;

public class Service implements Serializable {

	private static final long serialVersionUID = 9044165180845027037L;
	private String description;

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
