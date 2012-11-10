package uk.ac.glam.smartwps.shared.wcs111;

import uk.ac.glam.smartwps.shared.DataSource;

public class WCSDataSource extends DataSource {

	private static final long serialVersionUID = 8782706670196114205L;

	public WCSDataSource(String url) {
		setUrl(url);
	}

//	public WCSDataSource(WCS111 wcs) {
//		this(wcs.getServiceURL());
//		this.setName(wcs.getCapabilities().getServiceIdentification().getTitle().get(0).getValue());
//	}
}
