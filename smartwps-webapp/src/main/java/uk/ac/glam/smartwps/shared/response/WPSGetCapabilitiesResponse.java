package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.wps.ProcessDescriptor;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class WPSGetCapabilitiesResponse implements ServiceResponse {

	private List<ProcessDescriptor> processDescriptors;

	/**
	 * TODO: document
	 * @param processDescriptors
	 */
	public void setProcessDescriptors(List<ProcessDescriptor> processDescriptors) {
		this.processDescriptors = new ArrayList<ProcessDescriptor>(processDescriptors);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public ArrayList<ProcessDescriptor> getProcessDescriptors() {
		return new ArrayList<ProcessDescriptor>(processDescriptors);
	}

}
