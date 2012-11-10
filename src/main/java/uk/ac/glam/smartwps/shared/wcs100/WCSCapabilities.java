package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

public class WCSCapabilities implements Serializable {
	
	private static final long serialVersionUID = -3683473261251828873L;
	private ArrayList<CoverageOfferingBrief> coverageOfferings;
	private Service service;
	private String serviceURL;
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");

	public String getServiceURL() {
		return serviceURL;
	}

	public void setCoverageOfferings(ArrayList<CoverageOfferingBrief> coverageOfferings) {
		this.coverageOfferings = coverageOfferings;
	}
	
	public ArrayList<CoverageOfferingBrief> getCoverageOfferings() {
		return coverageOfferings;
	}

	public void setService(Service service) {
		LOGGER.info("Setting service for " + this);
		this.service = service;
	}

	public Service getService() {
		LOGGER.info("Getting service for " + this);
		return service;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
		// Set url of children
		for (Iterator<CoverageOfferingBrief> iterator = coverageOfferings.iterator(); iterator.hasNext();) {
			CoverageOfferingBrief cob = iterator.next();
			cob.setServiceURL(serviceURL);
		}
	}
}
