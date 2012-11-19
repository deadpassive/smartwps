package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.wps.ProcessDescriptor;

@SuppressWarnings("serial")
public class WPSGetCapabilitiesResponse implements ServiceResponse {

	private List<ProcessDescriptor> processDescriptors;

	public void setProcessDescriptors(List<ProcessDescriptor> processDescriptors) {
		this.processDescriptors = new ArrayList<ProcessDescriptor>(processDescriptors);
	}
	
	public ArrayList<ProcessDescriptor> getProcessDescriptors() {
		return new ArrayList<ProcessDescriptor>(processDescriptors);
	}

}
