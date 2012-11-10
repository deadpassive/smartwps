package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;

import uk.ac.glam.smartwps.shared.wps.ProcessDescriptor;

public class WPSGetCapabilitiesResponse implements ServiceResponse {

	private static final long serialVersionUID = -1605432463187746797L;
	private ArrayList<ProcessDescriptor> processDescriptors;

	public void setProcessDescriptors(
			ArrayList<ProcessDescriptor> processDescriptors) {
		this.processDescriptors = processDescriptors;
	}
	
	public ArrayList<ProcessDescriptor> getProcessDescriptors() {
		return processDescriptors;
	}

}
