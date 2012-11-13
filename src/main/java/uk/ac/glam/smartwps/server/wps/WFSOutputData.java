package uk.ac.glam.smartwps.server.wps;

/**
 * Used to store the XML metadata for a WFS process output (e.i. from a WPS
 * which supports WFS outputs directly).
 * 
 * @author Jon Britton
 */
public class WFSOutputData {
	private String featureTypeName;
	private String getCapsUrl;
	
	/**
	 * Create a new WFSOutputData 
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
