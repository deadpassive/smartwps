package uk.ac.glam.smartwps.wps.shared;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.data.shared.util.StringUtils;

/**
 * Metadata for a complex WPS input or output.
 * 
 * @author Jon Britton
 */
public class ComplexData extends WPSData {

    /**
	 * The default format.
	 */
	Format defaultFormat;
	/**
	 * List of supported formats.
	 */
	List<Format> supportedFormats;
	
	/**
	 * @return the default format of this data
	 */
	public Format getDefaultFormat() {
		return this.defaultFormat;
	}
	
	/**
	 * Set the default format of this WPS data.
	 * @param format the default format
	 */
	public void setDefaultFormat(Format format) {
		this.defaultFormat = format;
	}
	
	/**
	 * @return the list of supports for this data
	 */
	public List<Format> getSupportedFormats() {
		return new ArrayList<Format>(this.supportedFormats);
	}
	
	/**
	 * Set the supported formats for this WPS data.
	 * @param formats list of supports formats
	 */
	public void setSupportedFormats(List<Format> formats) {
		this.supportedFormats = new ArrayList<Format>(formats);
	}
	
	/**
	 * Check whether the given MIME type is supported.
	 * @param mimeType the MIME type to check for
	 * @return whether the given MIME type is supported
	 */
	public boolean supportsFormat(String mimeType) {
		if (this.defaultFormat != null) {
			// Must use contains because GeoServer uses a MIME type with subtype.
			if (this.defaultFormat.getMimeType().toLowerCase().contains(mimeType)) {
                return true;
            }
		}
		if (this.supportedFormats != null) {
            for (Format format : this.supportedFormats) {
                // Must use contains because GeoServer uses a MIME type with subtype.
				if (StringUtils.containsIgnoreCase(format.getMimeType(), mimeType)) {
                    return true;
                }
            }
		}
		return false;
	}

}
