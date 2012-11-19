package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class LiteralData extends WPSData {

	String dataType;
	private String defaultValue;
	private List<String> allowedValues;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
    @Override
	public String toString() {
		return "DataType: " + dataType;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setAllowedValues(List<String> allowedValues) {
		this.allowedValues = new ArrayList<String>(allowedValues);
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public List<String> getAllowedValues() {
		return new ArrayList<String>(allowedValues);
	}
	
}
