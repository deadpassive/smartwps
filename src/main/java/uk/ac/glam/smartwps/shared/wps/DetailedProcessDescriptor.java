package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;

/**
 * Contains deailed information about a WPS coverage.
 * @author Jon Britton
 *
 */
public class DetailedProcessDescriptor extends ProcessDescriptor {

	private static final long serialVersionUID = -543738588832941771L;
	private String abstrct;
	private ArrayList<WPSData> dataInputs;
	private ArrayList<WPSData> processOutputs;
	private boolean storeSupported;

	public String getAbstract() {
		return abstrct;
	}

	public void setAbstract(String abstrct) {
		this.abstrct = abstrct;
	}

	public ArrayList<WPSData> getDataInputs() {
		return dataInputs;
	}

	public void setDataInputs(ArrayList<WPSData> dataInputs) {
		this.dataInputs = dataInputs;
	}
	
	public void addDataInput(WPSData input) {
		dataInputs.add(input);
	}

	public ArrayList<WPSData> getProcessOutputs() {
		return processOutputs;
	}

	public void setProcessOutputs(ArrayList<WPSData> processOutputs) {
		this.processOutputs = processOutputs;
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
