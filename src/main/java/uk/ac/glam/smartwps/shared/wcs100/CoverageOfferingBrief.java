package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

public class CoverageOfferingBrief implements Serializable {

	private static final long serialVersionUID = -6416168038589111534L;
	private String description;
	private ArrayList<String> keywords;
	private String label;
	private BoundsSerializable lonLatEnvelope;
	protected String name;
	protected String serviceURL;

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}


	public String getDescription() {
		return description;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLonLatEnvelope(BoundsSerializable lonLatEnvelope) {
		this.lonLatEnvelope = lonLatEnvelope;
		this.lonLatEnvelope.setProjection("EPSG:4326"); // otherwise we get WGS84(DD) as the value!
	}

	public BoundsSerializable getLonLatEnvelope() {
		return lonLatEnvelope;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}
	
	public String getServiceURL() {
		return serviceURL;
	}

}
