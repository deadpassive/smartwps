package uk.ac.glam.smartwps.client.wps;

import uk.ac.glam.smartwps.shared.wps.output.ComplexProcessOutput;
import uk.ac.glam.smartwps.shared.wps.output.LiteralProcessOutput;
import uk.ac.glam.smartwps.shared.wps.output.ProcessOutput;
import static uk.ac.glam.smartwps.client.processresults.ProcessResultsView.*;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * A list grid record for displaying an output of a WPS process.
 * 
 * @author Jon Britton
 */
public class ProcessOutputRecord extends ListGridRecord {
	private ProcessOutput processOutput;
	
	/**
	 * Creates a new ProcessOutputRecord from the given ProcessOutput.
	 * @param output the process output
	 */
	public ProcessOutputRecord(ProcessOutput output) {
		this.processOutput = output;
		
		this.setAttribute(IDENTIFIER, output.getIdentifier());
		
		if (output instanceof ComplexProcessOutput) {
			ComplexProcessOutput complexOutput = (ComplexProcessOutput)output;
			
			this.setAttribute(TITLE, complexOutput.getTitle());
			this.setAttribute(MIME_TYPE, complexOutput.getMimeType());
		} else if (output instanceof LiteralProcessOutput) {
			LiteralProcessOutput literalOutput = (LiteralProcessOutput)output;
			this.setAttribute(VALUE, literalOutput.getValue());
		}
	}
	
	/**
	 * Returns the ProcessOutput object associated with this record.
	 * @return the ProcessOutput
	 */
	public ProcessOutput getProcessOutput() {
		return processOutput;
	}
}
