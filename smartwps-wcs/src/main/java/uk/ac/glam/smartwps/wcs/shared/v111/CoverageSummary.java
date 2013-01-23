package uk.ac.glam.smartwps.wcs.shared.v111;

import java.io.Serializable;

import uk.ac.glam.smartwps.base.shared.ows.BoundsSerializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class CoverageSummary implements Serializable {

	private String title;
	private String abstract1;
	private String identifier;
	private String serviceURL;
	private BoundsSerializable wgs84BoundingBox;

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
	 * @param wgs84BoundingBox
	 */
	public void setWGS84BoundingBox(BoundsSerializable wgs84BoundingBox) {
		this.wgs84BoundingBox = wgs84BoundingBox;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public BoundsSerializable getWGS84BoundingBox() {
		return wgs84BoundingBox;
	}

}
