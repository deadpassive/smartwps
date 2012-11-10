package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;
import java.util.logging.LogRecord;

public class RetrieveServerLogsResponse implements ServiceResponse {

	private static final long serialVersionUID = -4499387905026387838L;
	private ArrayList<LogRecord> logRecords;

	public void setLogRecords(ArrayList<LogRecord> logRecords) {
		this.logRecords = logRecords;
	}

	public ArrayList<LogRecord> getLogRecords() {
		return logRecords;
	}
	
}
