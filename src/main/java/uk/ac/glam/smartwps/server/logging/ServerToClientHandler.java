package uk.ac.glam.smartwps.server.logging;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ServerToClientHandler extends Handler {
	
	ArrayList<LogRecord> logBuffer = new ArrayList<LogRecord>();
	
	public ServerToClientHandler(Logger logger) {
		logger.addHandler(this);
	}

	@Override
	public void close() throws SecurityException {}

	@Override
	public void flush() {}

	@Override
	public void publish(LogRecord record) {
		logBuffer.add(record);
	}

	public ArrayList<LogRecord> getLogsAndClear() {
		ArrayList<LogRecord> logs = new ArrayList<LogRecord>(logBuffer);
		logBuffer.clear();
		return logs;
	}
	
	public void clearLogs() {
		logBuffer.clear();
	}

}
