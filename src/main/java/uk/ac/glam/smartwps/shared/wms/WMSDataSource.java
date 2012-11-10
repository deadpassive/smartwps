package uk.ac.glam.smartwps.shared.wms;

import uk.ac.glam.smartwps.shared.DataSource;

public class WMSDataSource extends DataSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269095560343289564L;

	public WMSDataSource(){}
	
	public WMSDataSource(String url) {
		setUrl(url);
	}

}
