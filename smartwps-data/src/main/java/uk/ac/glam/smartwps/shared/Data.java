package uk.ac.glam.smartwps.shared;

import java.io.Serializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public abstract class Data implements Serializable {

	DataSource dataSource;
	
	/**
	 * TODO: document
	 */
	public Data(){}

	/**
	 * TODO: document
	 * @return
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * TODO: document
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public String getServiceURL() {
		return dataSource.getUrl();
	}
}
