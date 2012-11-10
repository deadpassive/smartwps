package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;



public class LiteralData extends WPSData {

	private static final long serialVersionUID = 6289291612713706345L;
	String dataType;
	private String defaultValue;
	private ArrayList<String> allowedValues;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public String toString() {
		return "DataType: " + dataType;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setAllowedValues(ArrayList<String> allowedValues) {
		this.allowedValues = allowedValues;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public ArrayList<String> getAllowedValues() {
		return allowedValues;
	}
	
}
