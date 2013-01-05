package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class LiteralData extends WPSData {

	private String dataType;
	private String defaultValue;
	private List<String> allowedValues;

	/**
	 * TODO: document
	 * @return
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * TODO: document
	 * @param dataType
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
    @Override
	public String toString() {
		return "DataType: " + dataType;
	}

	/**
	 * TODO: document
	 * @param defaultValue
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * TODO: document
	 * @param allowedValues
	 */
	public void setAllowedValues(List<String> allowedValues) {
		this.allowedValues = new ArrayList<String>(allowedValues);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * TODO: document
	 * @return 
	 */
	public List<String> getAllowedValues() {
		return allowedValues == null ? null : Collections.unmodifiableList(allowedValues);
	}
	
}
