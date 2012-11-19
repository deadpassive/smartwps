package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class WCSCapabilities implements Serializable {
	
	private List<CoverageOfferingBrief> coverageOfferings;
	private Service service;
	private String serviceURL;
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");

	public String getServiceURL() {
		return serviceURL;
	}

	public void setCoverageOfferings(List<CoverageOfferingBrief> coverageOfferings) {
		this.coverageOfferings = new ArrayList<CoverageOfferingBrief>(coverageOfferings);
	}
	
	public List<CoverageOfferingBrief> getCoverageOfferings() {
		return new ArrayList<CoverageOfferingBrief>(coverageOfferings);
	}

	public void setService(Service service) {
		LOGGER.log(Level.INFO, "Setting service for {0}", this);
		this.service = service;
	}

	public Service getService() {
		LOGGER.log(Level.INFO, "Getting service for {0}", this);
		return service;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
		// Set url of children
        for (CoverageOfferingBrief cob : coverageOfferings) {
            cob.setServiceURL(serviceURL);
        }
	}
}
