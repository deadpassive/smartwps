package uk.ac.glam.smartwps.server.logging;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class ServerToClientHandler extends Handler {
	
	private ArrayList<LogRecord> logBuffer = new ArrayList<LogRecord>();
	
    /**
     * TODO: document
     * @param logger
     */
    public ServerToClientHandler(Logger logger) {
		logger.addHandler(this);
	}

	@Override
	public void close() throws SecurityException {
		// not implemented
	}

	@Override
	public void flush() {
		// not implemented
	}

	@Override
	public void publish(LogRecord record) {
		logBuffer.add(record);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public ArrayList<LogRecord> getLogsAndClear() {
		ArrayList<LogRecord> logs = new ArrayList<LogRecord>(logBuffer);
		logBuffer.clear();
		return logs;
	}
	
	/**
	 * TODO: document
	 */
	public void clearLogs() {
		logBuffer.clear();
	}

}
