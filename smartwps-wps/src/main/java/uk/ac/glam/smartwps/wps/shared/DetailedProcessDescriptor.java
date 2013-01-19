package uk.ac.glam.smartwps.wps.shared;

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

	/**
	 * @return the abstract description of this process
	 */
	public String getAbstract() {
		return this.abstrct;
	}

	/**
	 * Set the abstract description for this process.
	 * @param abs the abstract text
	 */
	public void setAbstract(String abs) {
		this.abstrct = abs;
	}

	/**
	 * @return the input data descriptions for this process
	 */
	public List<WPSData> getDataInputs() {
		return new ArrayList<WPSData>(this.dataInputs);
	}

	/**
	 * Set the input data descriptions for this process.
	 * @param inputs the list of input data descriptions
	 */
	public void setDataInputs(List<WPSData> inputs) {
		this.dataInputs = new ArrayList<WPSData>(inputs);
	}
	
	/**
	 * Add an input to this process.
	 * @param input the input description
	 */
	public void addDataInput(WPSData input) {
		this.dataInputs.add(input);
	}

	/**
	 * @return the output descriptions for this process
	 */
	public List<WPSData> getProcessOutputs() {
		return new ArrayList<WPSData>(this.processOutputs);
	}

	/**
	 * Set the output descriptions for this process.
	 * @param outputs list of output descriptions
	 */
	public void setProcessOutputs(List<WPSData> outputs) {
		this.processOutputs = new ArrayList<WPSData>(outputs);
	}

	/**
	 * Add an output to this process.
	 * @param output the output description
	 */
	public void addProcessOutput(WPSData output) {
		this.processOutputs.add(output);
	}

	/**
	 * Set whether this process supports stored outputs.
	 * @param store
	 */
	public void setStoreSupported(boolean store) {
		this.storeSupported = store;
	}
	
	/**
	 * @return whether this process supports stored outputs
	 */
	public boolean getStoreSupported() {
		return this.storeSupported;
	}
}
