package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class LiteralData extends WPSData {

	private String dataType;
	private String defaultValue;
	private List<String> allowedValues = new ArrayList<>();

	/**
	 * TODO: document
	 * @return the data type
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
	 * @param allowedValue
	 */
	public void addAllowedValue(String allowedValue) {
		this.allowedValues.add(allowedValue);
	}

	/**
	 * TODO: document
	 * @return the default data value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * TODO: document
	 * @return the list of allows values for this input
	 */
	public List<String> getAllowedValues() {
		return new ArrayList<>(allowedValues);
	}
	
}
