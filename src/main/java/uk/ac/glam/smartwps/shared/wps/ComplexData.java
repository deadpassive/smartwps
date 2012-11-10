package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;
import java.util.Iterator;

public class ComplexData extends WPSData {

	private static final long serialVersionUID = -6356251733821599344L;
	Format defaultFormat;
	ArrayList<Format> supportedFormats;
	
	public Format getDefaultFormat() {
		return defaultFormat;
	}
	public void setDefaultFormat(Format defaultFormat) {
		this.defaultFormat = defaultFormat;
	}
	public ArrayList<Format> getSupportedFormats() {
		return supportedFormats;
	}
	public void setSupportedFormats(ArrayList<Format> supportedFormats) {
		this.supportedFormats = supportedFormats;
	}
	
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
