package uk.ac.glam.smartwps.shared.wfs;

import uk.ac.glam.smartwps.shared.DataSource;

@SuppressWarnings("serial")
public class WFSDataSource extends DataSource {

	public WFSDataSource(){}
	
	public WFSDataSource(String url) {
		setUrl(url);
	}
}
