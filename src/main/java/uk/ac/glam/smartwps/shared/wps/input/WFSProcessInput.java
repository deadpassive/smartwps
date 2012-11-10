package uk.ac.glam.smartwps.shared.wps.input;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WFSProcessInput extends ComplexProcessInput {

	private static final long serialVersionUID = 7286023385684182580L;
	private String featureTypeName;
	private String serviceUrl;
	private String version;

	/**
	 * TODO: document
	 */
	public WFSProcessInput(){}
	
	/**
	 * TODO: document
	 * @param identifier
	 * @param featureType
	 */
	public WFSProcessInput(String identifier, WFSFeatureType featureType) {
		setId(identifier);
		this.featureTypeName = featureType.getTypeName();
		this.version = featureType.getWfsVersion();
		this.serviceUrl = featureType.getServiceURL();
	}

	/**
	 * @return the key-value-pair representation of this WFS input
	 */
	public String getKVPRequest() {
		String kvp = serviceUrl + "?SERVICE=WFS&REQUEST=GetFeature";
		kvp += "&TYPENAME=" + featureTypeName;
		kvp += "&VERSION=" + version;
		return kvp;
	}
	
	/**
	 * @return the schema of the process input
	 */
	public String getSchema() {
		// TODO: stop making such silly assumptions
		if (version.equals("1.0.0")) {
			return "http://schemas.opengis.net/gml/2.1.2/feature.xsd";
		}
		return null;
	}

}
