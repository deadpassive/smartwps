package uk.ac.glam.smartwps.shared.wps;

import java.io.Serializable;

/**
 * WPS input or output metadata. Doesn't represent an actual input or output - 
 * i.e. it doesn't describe the input data itself, just the metadata. Used to
 * transfer process metadata between the server and the client.
 * 
 * @author Jon Britton
 */
public abstract class WPSData implements Serializable {
	
	private String wpsAbstract;
	private String identifier;
	private String title;
	private int maxOccurs;
	private int minOccurs;

	/**
	 * @return the maximum number of times this input/output can occur
	 */
	public int getMaxOccurs() {
		return maxOccurs;
	}

	/**
	 * Set the maximum number of times this
	 * @param maxOccurs
	 */
	public void setMaxOccurs(int maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public int getMinOccurs() {
		return minOccurs;
	}

	/**
	 * TODO: document
	 * @param minOccurs
	 */
	public void setMinOccurs(int minOccurs) {
		this.minOccurs = minOccurs;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * TODO: document
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * TODO: document
	 * @param identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getAbstract() {
		return wpsAbstract;
	}

	/**
	 * TODO: document
	 * @param wpsAbstract
	 */
	public void setAbstract(String wpsAbstract) {
		this.wpsAbstract = wpsAbstract;
	}
	
}
