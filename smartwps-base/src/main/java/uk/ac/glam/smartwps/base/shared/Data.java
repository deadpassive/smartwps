package uk.ac.glam.smartwps.base.shared;

import java.io.Serializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public abstract class Data implements Serializable {

	/**
	 * TODO: document
	 */
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
