package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.LogRecord;

public class RetrieveServerLogsResponse implements ServiceResponse {

	private List<LogRecord> logRecords;

	public void setLogRecords(Collection<LogRecord> logRecords) {
		this.logRecords = new ArrayList<LogRecord>(logRecords);
	}

	public List<LogRecord> getLogRecords() {
		return new ArrayList<LogRecord>(logRecords);
	}
	
}
