package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WCSCapabilities111 implements Serializable {
	
	private List<CoverageSummary> contents;
	private String serviceURL;

	/**
	 * TODO: document
	 * @param contents
	 */
	public void setContents(List<CoverageSummary> contents) {
		this.contents = new ArrayList<CoverageSummary>(contents);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public ArrayList<CoverageSummary> getContents() {
		return new ArrayList<CoverageSummary>(contents);
	}

	/**
	 * TODO: document
	 * @param serviceURL
	 */
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
		
		// Set url of children
        for (CoverageSummary cs : contents) {
			cs.setServiceURL(serviceURL);
        }
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getServiceURL() {
		return serviceURL;		
	}

}
