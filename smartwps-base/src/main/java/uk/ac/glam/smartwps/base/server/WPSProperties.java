package uk.ac.glam.smartwps.base.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Application wide properties for the application. Loaded from /settings/config.txt on the server
 * side if it exists, otherwise loads defaults.
 * 
 * TODO: should defaults be hard-coded?
 * TODO: should be renamed something else since it doesn't really deal with just WPS.
 * 
 * @author Jon Britton
 */
public class WPSProperties extends Properties {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");
	
	private static WPSProperties instance;

	private WPSProperties(RemoteServiceServlet servlet) {
		FileInputStream in = null;
		try {
			File f = new File(servlet.getServletContext().getRealPath("settings") + "/config.txt");
			LOGGER.log(Level.INFO, "Loading settings from file {0}", f.getAbsolutePath());
			in = new FileInputStream(f);
			load(in);
		} catch (Exception e) {
			LOGGER.severe("Cannot load config file. Using defaults");
			this.setProperty("GEOSERVER_URL", "http://localhost:8080/geoserver");
			this.setProperty("GEOSERVER_URL_PUBLIC", "http://localhost:8080/geoserver");
			this.setProperty("GEOSERVER_USERNAME", "admin");
			this.setProperty("GEOSERVER_PASSWORD", "geoserver");
		} finally {
			try {
				if (in != null) {
                    in.close();
                }
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Failed to close properties input stream", e);
			}
		}
	}
	
	/**
	 * TODO: document
	 * @param servlet
	 */
	public static void initialiseProperties(RemoteServiceServlet servlet) {
		instance = new WPSProperties(servlet);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public static WPSProperties getProperties() {
		return instance;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getGeoServerURL() {
		return this.getProperty("GEOSERVER_URL");
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getPublicGeoServerURL() {
		return this.getProperty("GEOSERVER_URL_PUBLIC");
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getGeoserverUsername() {
		return this.getProperty("GEOSERVER_USERNAME");
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public String getGeoserverPassword() {
		return this.getProperty("GEOSERVER_PASSWORD");
	}

	/**
	 * TODO: document
	 * @param moduleBaseURL
	 */
	public void setModuleBaseURL(String moduleBaseURL) {
		this.setProperty("MODULE_BASE_URL", moduleBaseURL);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public String getModuleBaseURL() {
		return this.getProperty("MODULE_BASE_URL"); 
	}

}
