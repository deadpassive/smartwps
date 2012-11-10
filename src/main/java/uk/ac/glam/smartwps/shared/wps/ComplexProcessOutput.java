package uk.ac.glam.smartwps.shared.wps;


/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public abstract class ComplexProcessOutput extends ProcessOutput {

	private static final long serialVersionUID = 2601014296820584057L;
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
