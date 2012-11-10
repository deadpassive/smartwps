package uk.ac.glam.smartwps.shared.wfs;

import uk.ac.glam.smartwps.shared.DataSource;

public class WFSDataSource extends DataSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -630097213004266859L;

	public WFSDataSource(){}
	
	public WFSDataSource(String url) {
		setUrl(url);
	}

}
