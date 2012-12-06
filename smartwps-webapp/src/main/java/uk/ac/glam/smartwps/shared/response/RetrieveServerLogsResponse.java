package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.LogRecord;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class RetrieveServerLogsResponse implements ServiceResponse {

	private List<LogRecord> logRecords;

	/**
	 * TODO: document
	 * @param logRecords
	 */
	public void setLogRecords(Collection<LogRecord> logRecords) {
		this.logRecords = new ArrayList<LogRecord>(logRecords);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public List<LogRecord> getLogRecords() {
		return new ArrayList<LogRecord>(logRecords);
	}
	
}
