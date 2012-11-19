package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

@SuppressWarnings("serial")
public class CoverageSummary implements Serializable {

	private String title;
	private String abstract1;
	private String identifier;
	private String serviceURL;
	private BoundsSerializable wgs84BoundingBox;

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

	public void setWGS84BoundingBox(BoundsSerializable wgs84BoundingBox) {
		this.wgs84BoundingBox = wgs84BoundingBox;
	}

	public BoundsSerializable getWGS84BoundingBox() {
		return wgs84BoundingBox;
	}

}
