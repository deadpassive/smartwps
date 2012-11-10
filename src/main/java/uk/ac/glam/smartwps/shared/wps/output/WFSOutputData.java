package uk.ac.glam.smartwps.shared.wps.output;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WFSOutputData {
	private String featureTypeName;
	private String getCapsUrl;
	
	/**
	 * TODO: document
	 * @param name
	 * @param getCapsUrl
	 */
	public WFSOutputData(String name, String getCapsUrl) {
		this.featureTypeName = name;
		this.getCapsUrl = getCapsUrl;
	}

	/**
	 * TODO: document
	 * @return the WFS
	 */
	public String getFeatureTypeName() {
		return featureTypeName;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getGetCapsUrl() {
		return getCapsUrl;
	}
}
