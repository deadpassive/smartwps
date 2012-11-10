package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a complex WPS process input.
 * 
 * @author Jon Britton
 */
public class ComplexData extends WPSData {

	private static final long serialVersionUID = -6356251733821599344L;
	/**
	 * The default format.
	 */
	Format defaultFormat;
	/**
	 * List of supported formats.
	 */
	ArrayList<Format> supportedFormats;
	
	/**
	 * @return the default format of this data
	 */
	public Format getDefaultFormat() {
		return defaultFormat;
	}
	
	/**
	 * Set the default format of this WPS data.
	 * @param defaultFormat the default format
	 */
	public void setDefaultFormat(Format defaultFormat) {
		this.defaultFormat = defaultFormat;
	}
	
	/**
	 * @return the list of supports for this data
	 */
	public ArrayList<Format> getSupportedFormats() {
		return supportedFormats;
	}
	
	/**
	 * Set the supported formats for this WPS data.
	 * @param supportedFormats list of supports formats
	 */
	public void setSupportedFormats(ArrayList<Format> supportedFormats) {
		this.supportedFormats = supportedFormats;
	}
	
	/**
	 * Check whether the given MIME type is supported.
	 * @param mimeType the MIME type to check for
	 * @return whether the given MIME type is supported
	 */
	public boolean supportsFormat(String mimeType) {
		if (defaultFormat != null) {
			// Must use contains because GeoServer uses a MIME type with subtype.
			if (defaultFormat.getMimeType().toLowerCase().contains(mimeType))
				return true;
		}
		if (supportedFormats != null) {
			for (Iterator<Format> iterator = supportedFormats.iterator(); iterator.hasNext();) {
				Format format = iterator.next();
				// Must use contains because GeoServer uses a MIME type with subtype.
				if (format.getMimeType().toLowerCase().contains(mimeType))
					return true;
			}
		}
		return false;
	}

}
