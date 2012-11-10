package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;
import java.util.Date;

import uk.ac.glam.smartwps.shared.wps.output.ProcessOutput;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WPSExecuteResponse implements ServiceResponse {

	private static final long serialVersionUID = 8424232283251429384L;

	/**
	 * TODO: document
	 */
	public static final String WPS_CLIENT_EXCEPTION = "WPSClientException";
	
	private String processIdentifier;
	private String processTitle;
	private Date creationTime;
	private ArrayList<ProcessOutput> processOutputs;

	/**
	 * TODO: document
	 * @return
	 */
	public String getProcessTitle() {
		return processTitle;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public ArrayList<ProcessOutput> getProcessOutputs() {
		return processOutputs;
	}


	/**
	 * TODO: document
	 * @param processIdentifier
	 */
	public void setProcessIdentifier(String processIdentifier) {
		this.processIdentifier = processIdentifier;
	}

	/**
	 * TODO: document
	 * @param processTitle
	 */
	public void setProcessTitle(String processTitle) {
		this.processTitle = processTitle;
	}

	/**
	 * TODO: document
	 * @param creationTime
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * TODO: document
	 * @param processOutput
	 */
	public void addProcessOutput(ProcessOutput processOutput) {
		if (processOutputs == null)
			processOutputs = new ArrayList<ProcessOutput>();
		processOutputs.add(processOutput);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getProcessIdentifier() {
		return processIdentifier;
	}

}
