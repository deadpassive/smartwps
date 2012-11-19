package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class WCSCapabilities111 implements Serializable {
	
	private List<CoverageSummary> contents;
	private String serviceURL;

	public void setContents(List<CoverageSummary> contents) {
		this.contents = new ArrayList<CoverageSummary>(contents);
	}

	public ArrayList<CoverageSummary> getContents() {
		return new ArrayList<CoverageSummary>(contents);
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
		
		// Set url of children
        for (CoverageSummary cs : contents) {
			cs.setServiceURL(serviceURL);
        }
	}

	public String getServiceURL() {
		return serviceURL;		
	}

}
