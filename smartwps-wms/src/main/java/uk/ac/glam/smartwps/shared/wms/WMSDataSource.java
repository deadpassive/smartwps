package uk.ac.glam.smartwps.shared.wms;

import uk.ac.glam.smartwps.shared.DataSource;

@SuppressWarnings("serial")
public class WMSDataSource extends DataSource {

	public WMSDataSource(){}
	
	public WMSDataSource(String url) {
		setUrl(url);
	}
}
