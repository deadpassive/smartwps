package uk.ac.glam.smartwps.shared.wps.output;

/**
 * Base class for complex (i.e. not a literal) process outputs.
 * 
 * @author Jon Britton
 */
public abstract class ComplexProcessOutput extends ProcessOutput {

	private String title;
	private String mimeType;

	/**
	 * Set the title of this output
	 * @param title the output's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title of this output
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the MIME type of this output
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Set the MIME type of this output.
	 * @param mimeType the MIME type
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
