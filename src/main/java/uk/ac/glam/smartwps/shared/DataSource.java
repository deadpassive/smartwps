package uk.ac.glam.smartwps.shared;

import java.io.Serializable;

public abstract class DataSource implements Serializable {

    private String name;
	private String url;

	public DataSource() {}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
