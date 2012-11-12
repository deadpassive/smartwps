package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

@SuppressWarnings("serial")
public class CoverageOfferingBrief implements Serializable {

	private String description;
	private List<String> keywords;
	private String label;
	private BoundsSerializable lonLatEnvelope;
	protected String name;
	protected String serviceURL;

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getKeywords() {
		return new ArrayList<String>(keywords);
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = new ArrayList<String>(keywords);
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
