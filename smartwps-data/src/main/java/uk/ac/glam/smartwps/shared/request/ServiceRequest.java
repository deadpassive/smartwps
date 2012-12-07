package uk.ac.glam.smartwps.shared.request;

import java.io.Serializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public abstract class ServiceRequest implements Serializable {

	private String url;

	/**
	 * TODO: document
	 * @param url
	 */
	public ServiceRequest(String url) {
		this.setServiceUrl(url);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getServiceUrl() {
		return url;
	}

	/**
	 * TODO: document
	 * @param url
	 */
	public final void setServiceUrl(String url) {
		this.url = url;
	}
}
