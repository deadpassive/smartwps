package uk.ac.glam.smartwps.wfs.shared;

import uk.ac.glam.smartwps.base.shared.Data;

/**
 * Contains basic information describing a WFS feature type. Only contains info
 * contained in a GetCapabilities response.
 * 
 * @author Jon
 */
@SuppressWarnings("serial")
public class WFSFeatureTypeBase extends Data {

	private String typeName;
	private String title;
	private String abs;
	private String name;
	private String wfsVersion;

	/**
	 * Set the type name for this feature type. Usually of the form "ns:name",
	 * where "ns" is the namespace shorthand and "name" is the feature name.
	 * 
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * Returns the type name for this feature type. Usually of the form
	 * "ns:name", where "ns" is the namespace shorthand and "name" is the
	 * feature name.
	 * 
	 * @return the type name
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * Set the human readable title for this feature type.
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the human readable title for this feature type.
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the abstract for this datatype.
	 * 
	 * @param abs
	 *            abstract
	 */
	public void setAbstract(String abs) {
		this.abs = abs;
	}

	/**
	 * Get the abstract for this datatype
	 * 
	 * @return abstract
	 */
	public String getAbstract() {
		return abs;
	}

	/**
	 * Sets the local name of this feature type. E.g. if the type name is
	 * "sf:streams" then the local name is "streams".
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the local name of this feature type. E.g. if the type name is
	 * "sf:streams" then the local name is "streams".
	 * 
	 * @return the local name
	 */
	public String getName() {
		return name;
	}

	/**
	 * TODO: document
	 * @param wfsVersion
	 */
	public void setWfsVersion(String wfsVersion) {
		this.wfsVersion = wfsVersion;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getWfsVersion() {
		return wfsVersion;
	}
}
