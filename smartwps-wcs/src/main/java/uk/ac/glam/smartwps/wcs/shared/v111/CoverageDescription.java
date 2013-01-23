package uk.ac.glam.smartwps.wcs.shared.v111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.base.shared.ows.BoundsSerializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class CoverageDescription implements Serializable {

	private String title;
	private String abstract1;
	private String identifier;
	private String serviceURL;
	private List<String> supportedCRSs;
	private CoverageSummary coverageSummary;
	private Domain domain;

	/**
	 * TODO: document
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * TODO: document
	 * @param abstract1
	 */
	public void setAbstract(String abstract1) {
		this.abstract1 = abstract1;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getAbstract() {
		return abstract1;
	}

	/**
	 * TODO: document
	 * @param identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * TODO: document
	 * @param serviceURL
	 */
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getServiceURL() {
		return serviceURL;
	}

	/**
	 * TODO: document
	 * @param supportedCRSs
	 */
	public void setSupportedCRSs(List<String> supportedCRSs) {
		this.supportedCRSs = new ArrayList<String>(supportedCRSs);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public ArrayList<String> getSupportedCRSs() {
		return new ArrayList<String>(supportedCRSs);
	}


	/**
	 * TODO: document
	 * @param coverageSummary
	 */
	public void setCoverageSummary(CoverageSummary coverageSummary) {
		this.coverageSummary = coverageSummary;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public CoverageSummary getCoverageSummary() {
		return coverageSummary;
	}

	/**
	 * TODO: document
	 * @param domain
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public Domain getDomain() {
		return domain;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public BoundsSerializable getDefaultBoundingBox() {
		BoundsSerializable bbox = null;
		// If this has a GridCRS, use that
		String crs = domain.getSpatialDomain().getGridBaseCRS();
		if (crs != null) {
			bbox = domain.getSpatialDomain().getBoundingBox(crs);
		}
		
		if (bbox == null) {
			// GridCRS doesn't exist - just use the first BoundingBox
			bbox = domain.getSpatialDomain().getBoundingBoxList().get(0);
		}
		
		return bbox;
	}
}
