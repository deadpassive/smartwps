package uk.ac.glam.smartwps.wps.client;

import uk.ac.glam.smartwps.wps.shared.ProcessDescriptor;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * A record for displaying data from a ProcessDescription object.
 * 
 * @author Jon Britton
 */
public class ProcessRecord extends ListGridRecord {

	private ProcessDescriptor processDescriptor;

	/**
	 * Creates a new ProcessRecord from the given ProcessDescriptor.
	 * @param descriptor
	 */
	public ProcessRecord(ProcessDescriptor descriptor) {
		this.processDescriptor = descriptor;
		this.setAttribute("id", descriptor.getId());
		this.setAttribute("title", descriptor.getTitle());
	}

	/**
	 * @return the ProcessDescription associated with this record.
	 */
	public ProcessDescriptor getProcessDescriptor() {
		return processDescriptor;
	}
}