package uk.ac.glam.smartwps.client;

import java.util.HashMap;

import uk.ac.glam.smartwps.shared.DataSource;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class DataSourceManager {

    private DataSourceManager() {}
    
	private static HashMap<String,DataSource> datasourceMap = new HashMap<String,DataSource>();
	
	/**
	 * Get the data store for the given service url.
	 * @param url the service url
	 * @return the data store for the url
	 */
	public static DataSource getDataSource(String url) {
		return datasourceMap.get(url);
	}
	
	/**
	 * TODO: document
	 * @param url
	 * @param ds
	 */
	public static void registerDataSource(String url, DataSource ds) {
		datasourceMap.put(url, ds);
	}

    
}
