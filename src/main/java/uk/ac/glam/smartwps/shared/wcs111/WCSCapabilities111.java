package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class WCSCapabilities111 implements Serializable {
	
	private static final long serialVersionUID = -1514644457507829216L;
	private ArrayList<CoverageSummary> contents;
	private String serviceURL;

	public void setContents(ArrayList<CoverageSummary> contents) {
		this.contents = contents;
	}

	public ArrayList<CoverageSummary> getContents() {
		return contents;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
		
		// Set url of children
		for (Iterator<CoverageSummary> iterator = contents.iterator(); iterator.hasNext();) {
			CoverageSummary cs = iterator.next();
			cs.setServiceURL(serviceURL);
		}
	}

	public String getServiceURL() {
		return serviceURL;		
	}

}
