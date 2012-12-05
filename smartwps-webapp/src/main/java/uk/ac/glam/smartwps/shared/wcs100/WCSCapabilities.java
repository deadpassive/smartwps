package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WCSCapabilities implements Serializable {
	
	private List<CoverageOfferingBrief> coverageOfferings;
	private Service service;
	private String serviceURL;
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");

	/**
	 * TODO: document
	 * @return
	 */
	public String getServiceURL() {
		return serviceURL;
	}

	/**
	 * TODO: document
	 * @param coverageOfferings
	 */
	public void setCoverageOfferings(List<CoverageOfferingBrief> coverageOfferings) {
		this.coverageOfferings = new ArrayList<CoverageOfferingBrief>(coverageOfferings);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public List<CoverageOfferingBrief> getCoverageOfferings() {
		return new ArrayList<CoverageOfferingBrief>(coverageOfferings);
	}

	public void setService(Service service) {
		LOGGER.log(Level.INFO, "Setting service for {0}", this);
		this.service = service;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public Service getService() {
		LOGGER.log(Level.INFO, "Getting service for {0}", this);
		return service;
	}

	/**
	 * TODO: document
	 * @param serviceURL
	 */
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
		// Set url of children
        for (CoverageOfferingBrief cob : coverageOfferings) {
            cob.setServiceURL(serviceURL);
        }
	}
}
