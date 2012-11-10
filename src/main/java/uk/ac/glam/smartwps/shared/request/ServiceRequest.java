package uk.ac.glam.smartwps.shared.request;

import java.io.Serializable;

public abstract class ServiceRequest implements Serializable {

	private static final long serialVersionUID = -5296449736685143156L;
	private String url;

	public ServiceRequest(String url) {
		this.setServiceUrl(url);
	}

	public String getServiceUrl() {
		return url;
	}

	public void setServiceUrl(String url) {
		this.url = url;
	}
}
