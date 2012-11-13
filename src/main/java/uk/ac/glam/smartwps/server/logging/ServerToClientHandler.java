package uk.ac.glam.smartwps.server.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class ServerToClientHandler extends Handler {
	
	private List<LogRecord> logBuffer = new ArrayList<>();
	
    /**
     * TODO: document
     * @param logger
     */
    public ServerToClientHandler(Logger logger) {
		logger.addHandler(this);
	}

	@Override
	public void close() throws SecurityException {
		// do nothing
	}

	@Override
	public void flush() {
		// do nothing
	}

	@Override
	public void publish(LogRecord record) {
		logBuffer.add(record);
	}

	/**
	 * TODO: document
	 * @return the cached logs
	 */
	public List<LogRecord> getLogsAndClear() {
		List<LogRecord> logs = new ArrayList<>(logBuffer);
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
