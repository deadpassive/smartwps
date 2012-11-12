package uk.ac.glam.smartwps.shared.request;

import java.io.Serializable;

public abstract class ServiceRequest implements Serializable {

	private String url;

	public ServiceRequest(String url) {
		this.setServiceUrl(url);
	}

	public String getServiceUrl() {
		return url;
	}

	public final void setServiceUrl(String url) {
		this.url = url;
	}
}
