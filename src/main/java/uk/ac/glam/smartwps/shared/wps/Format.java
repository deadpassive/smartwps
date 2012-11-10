package uk.ac.glam.smartwps.shared.wps;

import java.io.Serializable;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class Format implements Serializable {
	
	/**
	 * TODO: document
	 */
	public static final String APPLICATION_WFS = "application/WFS";
	/**
	 * TODO: document
	 */
	public static final String APPLICATION_WCS = "application/WCS";
	/**
	 * TODO: document
	 */
	public static final String TEXT_XML = "text/xml";
	/**
	 * TODO: document
	 */
	public static final String APPLICATION_X_ZIPPED_SHP = "application/x-zipped-shp";
	/**
	 * TODO: document
	 */
	public static final String IMAGE_TIFF = "image/tiff";

	private static final long serialVersionUID = 4819878866366513541L;
	private String mimeType;
	private String schema;
	private String encoding;
	
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
	 * @return
	 */
	public String getSchema() {
		return schema;
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
		return encoding;
	}
	/**
	 * TODO: document
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	
	
}
