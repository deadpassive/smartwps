package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;
import java.util.List;
import uk.ac.glam.smartwps.shared.util.StringUtils;

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
	public List<Format> getSupportedFormats() {
		return new ArrayList<Format>(supportedFormats);
	}
	
	/**
	 * Set the supported formats for this WPS data.
	 * @param supportedFormats list of supports formats
	 */
	public void setSupportedFormats(List<Format> supportedFormats) {
		this.supportedFormats = new ArrayList<Format>(supportedFormats);
	}
	
	/**
	 * Check whether the given MIME type is supported.
	 * @param mimeType the MIME type to check for
	 * @return whether the given MIME type is supported
	 */
	public boolean supportsFormat(String mimeType) {
		if (defaultFormat != null) {
			// Must use contains because GeoServer uses a MIME type with subtype.
			if (defaultFormat.getMimeType().toLowerCase().contains(mimeType)) {
                return true;
            }
		}
		if (supportedFormats != null) {
            for (Format format : supportedFormats) {
                // Must use contains because GeoServer uses a MIME type with subtype.
				if (StringUtils.containsIgnoreCase(format.getMimeType(), mimeType)) {
                    return true;
                }
            }
		}
		return false;
	}

}
