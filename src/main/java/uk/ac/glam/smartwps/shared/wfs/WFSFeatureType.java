package uk.ac.glam.smartwps.shared.wfs;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

/**
 * Contains basic information describing a WFS feature type, including info 
 * from a DescribeFeatureType response.
 * 
 * @author Jon
 *
 */
public class WFSFeatureType extends WFSFeatureTypeBase {

	private static final long serialVersionUID = 6346070750442672772L;
	private BoundsSerializable bounds;
	private String namespaceURI;

	/**
	 * Returns the namespace URI, useful for creating GetFeature requests.
	 * @return the namespace URI
	 */
	public String getNamespaceURI() {
		return namespaceURI;
	}

	/**
	 * Set the bounding box of this feature type.
	 * @param bounds the feature type bounds
	 */
	public void setBounds(BoundsSerializable bounds) {
		this.bounds = bounds;
	}
	
	/**
	 * Returns the bounding box of this feature type.
	 * @return the feature type bounds
	 */
	public BoundsSerializable getBounds() {
		return bounds;
	}

	/**
	 * Set the namespace URI.
	 * @param namespaceURI
	 */
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}
}
