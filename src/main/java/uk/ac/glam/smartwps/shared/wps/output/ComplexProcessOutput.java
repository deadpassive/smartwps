package uk.ac.glam.smartwps.shared.wps.output;



/**
 * Represents a complex (i.e. not a literal) process output.
 * 
 * @author Jon Britton
 */
public abstract class ComplexProcessOutput extends ProcessOutput {

	private String title;
	private String mimeType;
	private String layerName;


	/**
	 * TODO: document
	 * @return
	 */
	public String getLayerName() {
		return layerName;
	}

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
	 * @return
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * TODO: document
	 * @param mimeType
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * TODO: document
	 * @param layerName
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}
}
