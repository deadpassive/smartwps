package uk.ac.glam.smartwps.shared.wps;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains detailed information about a WPS coverage.
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class DetailedProcessDescriptor extends ProcessDescriptor {

	private String abstrct;
	private List<WPSData> dataInputs = new ArrayList<>();
	private List<WPSData> processOutputs;
	private boolean storeSupported;

	/**
	 * @return the process abstract
	 */
	public String getAbstract() {
		return abstrct;
	}

	/**
	 * Set the process abstract text
	 * @param abstrct the process abstract
	 */
	public void setAbstract(String abstrct) {
		this.abstrct = abstrct;
	}

	/**
	 * @return the process inputs
	 */
	public List<WPSData> getDataInputs() {
		return new ArrayList<>(dataInputs);
	}
	
	/**
	 * Add an input to this process
	 * @param input
	 */
	public void addDataInput(WPSData input) {
		dataInputs.add(input);
	}

	/**
	 * @return the process outputs
	 */
	public List<WPSData> getProcessOutputs() {
		return new ArrayList<>(processOutputs);
	}

	/**
	 * Add a process output to this process
	 * @param output the process output
	 */
	public void addProcessOutput(WPSData output) {
		processOutputs.add(output);
	}

	/**
	 * Set whether store is supported for this process
	 * @param storeSupported
	 */
	public void setStoreSupported(boolean storeSupported) {
		this.storeSupported = storeSupported;
	}
	
	/**
	 * @return whether output storing is supported
	 */
	public boolean getStoreSupported() {
		return storeSupported;
	}
}
