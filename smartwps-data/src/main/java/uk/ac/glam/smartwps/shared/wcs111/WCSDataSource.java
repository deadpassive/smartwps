package uk.ac.glam.smartwps.shared.wcs111;

import uk.ac.glam.smartwps.shared.DataSource;

@SuppressWarnings("serial")
public class WCSDataSource extends DataSource {

	public WCSDataSource(String url) {
		setUrl(url);
	}
}
