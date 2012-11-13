package uk.ac.glam.smartwps.shared.wps;

import java.io.Serializable;

/**
 * Contains basic information about a WPS coverage. This simple class is used for 
 * RPC and contains as little data as possible to reduce transfer times.
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class ProcessDescriptor implements Serializable {
	
	/**
	 * TODO: document
	 */
	String id;
	/**
	 * TODO: document
	 */
	String title;
	/**
	 * TODO: document
	 */
	String serviceURL;
	/**
	 * TODO: document
	 */
	String processVersion;
	
	/**
	 * Returns the process version.
	 * @return the process version
	 */
	public String getProcessVersion() {
		return processVersion;
	}

	/**
	 * Set the process version.
	 * @param processVersion
	 */
	public void setProcessVersion(String processVersion) {
		this.processVersion = processVersion;
	}

	/**
	 * Get's the service URL for this processes parent WPS.
	 * @return the service URL
	 */
	public String getServiceURL() {
		return serviceURL;
	}

	/**
	 * Get's the service URL for this processes parent WPS.
	 * @param serviceURL
	 */
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	/**
	 * Empty constructor for serialization
	 */
	public ProcessDescriptor(){}

	/**
	 * Returns the identifier for this WPS process.
	 * @return process ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the identifier for this WPS process.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the human-readable title for this WPS process.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "id: " + id + ", title: " + title;
	}

	/**
	 * Gets the human-readable title for this WPS process.
	 * @return process title.
	 */
	public String getTitle() {
		return title;
	}
}
