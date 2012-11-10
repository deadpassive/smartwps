package uk.ac.glam.smartwps.shared.wps;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WFSOutput {
	private String resourceId;
	private String getCapsUrl;
	
	/**
	 * TODO: document
	 * @param resourceId
	 * @param getCapsUrl
	 */
	public WFSOutput(String resourceId, String getCapsUrl) {
		this.resourceId = resourceId;
		this.getCapsUrl = getCapsUrl;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getGetCapsUrl() {
		return getCapsUrl;
	}
}
