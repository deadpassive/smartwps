package uk.ac.glam.smartwps.wps.shared;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.base.shared.ServiceRequest;
import uk.ac.glam.smartwps.wps.shared.input.ProcessInput;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WPSExecuteRequest extends ServiceRequest {

	private List<ProcessInput> inputs;
	private DetailedProcessDescriptor processDescriptor;
	
	/**
	 * TODO: document
	 */
	public WPSExecuteRequest() {
		super(null);
	}
	
	/**
	 * TODO: document
	 * @param url
	 */
	public WPSExecuteRequest(String url){
		super(url);
		inputs = new ArrayList<ProcessInput>();
	}

	/**
	 * Create an execute request for the given process
	 * 
     * @param processDescriptor 
	 */
	public WPSExecuteRequest(DetailedProcessDescriptor processDescriptor) {
		this(processDescriptor.getServiceURL());
		this.processDescriptor = processDescriptor;
	}

	/**
	 * Stores a data type (usually WCS, WFS...) for use in a WPS Execute
	 * request. Data inputs are stored and sent to the server where a real WPS
	 * request is made.
	 * 
	 * @param data
	 */
	public void addDataInput(ProcessInput data) {
		inputs.add(data);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public DetailedProcessDescriptor getProcessDescriptor() {
		return processDescriptor;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public ArrayList<ProcessInput> getInputs() {
		return new ArrayList<ProcessInput>(inputs);
	}

}
