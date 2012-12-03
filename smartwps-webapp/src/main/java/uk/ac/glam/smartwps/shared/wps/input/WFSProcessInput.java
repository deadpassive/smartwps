package uk.ac.glam.smartwps.shared.wps.input;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;

/**
 * A WFS input to a WPS process.
 * 
 * @author Jon Britton
 */
public class WFSProcessInput extends ComplexProcessInput {

	private String featureTypeName;
	private String serviceUrl;
	private String version;

	/**
	 * Empty constructor for serialisation.
	 */
	public WFSProcessInput(){
		super(null);
	}
	
	/**
	 * Create a new WFS process input with the given identifier and associated WFS feature type.
	 * @param identifier the input ID
	 * @param featureType the WFS feature type to use as input
	 */
	public WFSProcessInput(String identifier, WFSFeatureType featureType) {
		super(identifier);
		this.featureTypeName = featureType.getTypeName();
		this.version = featureType.getWfsVersion();
		this.serviceUrl = featureType.getServiceURL();
	}

	/**
	 * @return the key-value-pair representation of this WFS input
	 */
	public String getKVPRequest() {
		StringBuilder kvp = new StringBuilder(serviceUrl).append("?SERVICE=WFS&REQUEST=GetFeature")
				.append("&TYPENAME=").append(featureTypeName)
				.append("&VERSION=").append(version)
				.append("&SRSNAME=EPSG:4326");	// Must force SRS to avoid axis ordering issue. TODO: fix.
		return kvp.toString();
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
