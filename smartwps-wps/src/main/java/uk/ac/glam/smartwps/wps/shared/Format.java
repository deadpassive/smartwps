package uk.ac.glam.smartwps.wps.shared;

import java.io.Serializable;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class Format implements Serializable {
	
	/**
	 * MIME type application/WFS.
	 */
	public static final String APPLICATION_WFS = "application/WFS";
	/**
	 * MIME type application/WCS.
	 */
	public static final String APPLICATION_WCS = "application/WCS";
	/**
	 * MIME type text/xml.
	 */
	public static final String TEXT_XML = "text/xml";
	/**
	 * MIME type application/x-shipped-shp.
	 */
	public static final String APPLICATION_X_ZIPPED_SHP = "application/x-zipped-shp";
	/**
	 * MIME type image/tiff.
	 */
	public static final String IMAGE_TIFF = "image/tiff";

	private String mimeType;
	private String schema;
	private String encoding;
	
	/**
	 * @return the MIME type of this format
	 */
	public String getMimeType() {
		return this.mimeType;
	}
	
	/**
	 * Set the MIME type for this format.
	 * @param mimeType the MIME type
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public String getSchema() {
		return this.schema;
	}
	
	/**
	 * TODO: document
	 * @param schema
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public String getEncoding() {
		return this.encoding;
	}
	
	/**
	 * TODO: document
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
