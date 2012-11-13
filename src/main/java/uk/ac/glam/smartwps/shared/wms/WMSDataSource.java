package uk.ac.glam.smartwps.shared.wms;

import uk.ac.glam.smartwps.shared.DataSource;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class WMSDataSource extends DataSource {

	/**
	 * TODO: document
	 */
	public WMSDataSource(){}
	
	/**
	 * TODO: document
	 * @param url
	 */
	public WMSDataSource(String url) {
		setUrl(url);
	}
}
