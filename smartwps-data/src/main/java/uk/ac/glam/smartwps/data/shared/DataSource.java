package uk.ac.glam.smartwps.data.shared;

import java.io.Serializable;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public abstract class DataSource implements Serializable {

    private String name;
	private String url;

	/**
	 * TODO: document
	 */
	public DataSource() {}
	
	/**
	 * TODO: document
	 * @return the name of the data source
	 */
	public String getName() {
		return name;
	}

	/**
	 * TODO: document
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the URL of the data source
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * TODO: document
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
