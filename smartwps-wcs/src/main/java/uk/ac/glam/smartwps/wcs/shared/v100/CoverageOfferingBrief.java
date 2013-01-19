package uk.ac.glam.smartwps.wcs.shared.v100;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.data.shared.ows.BoundsSerializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class CoverageOfferingBrief implements Serializable {

	private String description;
	private List<String> keywords;
	private String label;
	private BoundsSerializable lonLatEnvelope;
	/**
	 * TODO: document
	 */
	protected String name;
	/**
	 * TODO: document
	 */
	protected String serviceURL;

	/**
	 * TODO: document
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public List<String> getKeywords() {
		return new ArrayList<String>(keywords);
	}

	/**
	 * TODO: document
	 * @param keywords
	 */
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = new ArrayList<String>(keywords);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * TODO: document
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * TODO: document
	 * @param lonLatEnvelope
	 */
	public void setLonLatEnvelope(BoundsSerializable lonLatEnvelope) {
		this.lonLatEnvelope = lonLatEnvelope;
		this.lonLatEnvelope.setProjection("EPSG:4326"); // otherwise we get WGS84(DD) as the value!
	}

	/**
	 * TODO: document
	 * @return
	 */
	public BoundsSerializable getLonLatEnvelope() {
		return lonLatEnvelope;
	}

	/**
	 * TODO: document
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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

}
