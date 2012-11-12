package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

@SuppressWarnings("serial")
public class CoverageDescription implements Serializable {

	private String title;
	private String abstract1;
	private String identifier;
	private String serviceURL;
	private List<String> supportedCRSs;
	private CoverageSummary coverageSummary;
	private Domain domain;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setAbstract(String abstract1) {
		this.abstract1 = abstract1;
	}

	public String getAbstract() {
		return abstract1;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public void setSupportedCRSs(List<String> supportedCRSs) {
		this.supportedCRSs = new ArrayList<String>(supportedCRSs);
	}

	public ArrayList<String> getSupportedCRSs() {
		return new ArrayList<String>(supportedCRSs);
	}


	public void setCoverageSummary(CoverageSummary coverageSummary) {
		this.coverageSummary = coverageSummary;
	}

	public CoverageSummary getCoverageSummary() {
		return coverageSummary;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Domain getDomain() {
		return domain;
	}
	
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
