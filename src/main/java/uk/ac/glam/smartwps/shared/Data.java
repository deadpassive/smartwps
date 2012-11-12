package uk.ac.glam.smartwps.shared;

import java.io.Serializable;

public abstract class Data implements Serializable {

	DataSource dataSource;
	
	public Data(){}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public String getServiceURL() {
		return dataSource.getUrl();
	}
}
