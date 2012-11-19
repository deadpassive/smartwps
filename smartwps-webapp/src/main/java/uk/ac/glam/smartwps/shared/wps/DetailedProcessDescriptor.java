package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains detailed information about a WPS coverage.
 * 
 * @author Jon Britton
 */
public class DetailedProcessDescriptor extends ProcessDescriptor {

	private String abstrct;
	private List<WPSData> dataInputs;
	private List<WPSData> processOutputs;
	private boolean storeSupported;

	public String getAbstract() {
		return abstrct;
	}

	public void setAbstract(String abstrct) {
		this.abstrct = abstrct;
	}

	public List<WPSData> getDataInputs() {
		return new ArrayList<WPSData>(dataInputs);
	}

	public void setDataInputs(List<WPSData> dataInputs) {
		this.dataInputs = new ArrayList<WPSData>(dataInputs);
	}
	
	public void addDataInput(WPSData input) {
		dataInputs.add(input);
	}

	public List<WPSData> getProcessOutputs() {
		return new ArrayList<WPSData>(processOutputs);
	}

	public void setProcessOutputs(List<WPSData> processOutputs) {
		this.processOutputs = new ArrayList<WPSData>(processOutputs);
	}

	public void addProcessOutput(WPSData output) {
		processOutputs.add(output);
	}

	public void setStoreSupported(boolean storeSupported) {
		this.storeSupported = storeSupported;
	}
	
	public boolean getStoreSupported() {
		return storeSupported;
	}
}
