package uk.ac.glam.smartwps.client.loggerdialog;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import uk.ac.glam.smartwps.base.shared.response.RetrieveServerLogsResponse;
import uk.ac.glam.smartwps.client.SmartWPS;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import java.util.List;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class LogViewer extends VLayout {
	
	private final Logger logger = Logger.getLogger("LogViewer");
	private Timer timer;
	private Handler handler;
    private StringBuilder logBuilder = new StringBuilder();

	/**
	 * TODO: document
	 */
	public LogViewer() {
		ToolStrip toolStrip = new ToolStrip();
		toolStrip.setWidth100();
		toolStrip.addFill();
		
		// Enabled server logging?
		final CheckboxItem serverLogging = new CheckboxItem();
		serverLogging.setTitle("Server logging?");
		serverLogging.setDefaultValue(false);
		serverLogging.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				if ((Boolean) serverLogging.getValue()) {
					startServerLogging();
				} else {
					stopServerLogging();
				}
					
			}
			
		});
		toolStrip.addFormItem(serverLogging);
		
		// Logging level selector
		final SelectItem selectLevel = new SelectItem();  
		selectLevel.setHeight(19);  
		selectLevel.setName("selectFont");  
		selectLevel.setWidth(120);  
		selectLevel.setShowTitle(false);  
		selectLevel.setValueMap("ALL", "FINEST", "FINER", "FINE", "CONFIG", "INFO", "WARNING", "SEVERE", "OFF");  
		selectLevel.setDefaultValue("INFO");
        selectLevel.addChangedHandler(new ChangedHandler() {  
            @Override
			public void onChanged(ChangedEvent event) {  
            	Level level = Level.parse((String) selectLevel.getValue());
            	logger.info("Changed logging level to " + level.getName());
            	logger.setLevel(level);
            }  
        });
		toolStrip.addFormItem(selectLevel);

		addMember(toolStrip);
		
		// Log view
		final HTMLPane htmlPane = new HTMLPane();
		htmlPane.setWidth100();
		htmlPane.setHeight100();
		htmlPane.setContents("");
		addMember(htmlPane);
		
		handler = new Handler() {
			
			@Override
			public void publish(LogRecord record) {
				htmlPane.scrollToBottom();
				String colour = "black";
				String message = record.getMessage();
				// TODO: Why isn't this working?
				message = message.replaceAll("<", "&lt;");
				message = message.replaceAll(">", "&gt;");
				if (record.getLevel() == Level.SEVERE) {
                    colour = "darkred";
                }
                logBuilder.append("<span style=\"font-family:courier;color:").append(colour).append("\"><b>")
                        .append(record.getLoggerName()).append(" (").append(record.getLevel()).append("):</b> ")
                        .append(message).append("</span><br/>");
				if (record.getThrown() != null) {
					logBuilder.append("<span style=\"font-family:courier;color:red\">");
					Throwable t = record.getThrown();
					logBuilder.append(t.getClass().getName()).append(": ").append(t.getMessage()).append("<br/>");
					StackTraceElement[] stes = t.getStackTrace();
					for (int i = 0; i < stes.length; i++) {
						StackTraceElement ste = stes[i];
						logBuilder.append("&nbsp;&nbsp;&nbsp;").append(ste.toString()).append("<br/>");
					}
					logBuilder.append("</span>");
				}
				htmlPane.setContents(logBuilder.toString());
				htmlPane.scrollToBottom();
			}
			
			@Override
			public void flush() {
				// Do nothing
			}
			
			@Override
			public void close() {
				// Do nothing
			}
		};
		
		logger.addHandler(handler);
	}
	
	/**
	 * TODO: document
	 */
	public void startServerLogging() {
		logger.info("Started logging from server");
		timer = new Timer() {
			
			@Override
			public void run() {
				updateServerLogs();
			}
		};
				
		timer.scheduleRepeating(3000);
	}
	
	/**
	 * TODO: document
	 */
	public void stopServerLogging() {
		if (timer != null) {
            timer.cancel();
        }
	}
	
	private void updateServerLogs() {
		// Set up the callback object.
		AsyncCallback<RetrieveServerLogsResponse> callback = new AsyncCallback<RetrieveServerLogsResponse>() {
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				SC.say("Failed to contact server");
			}

			@Override
			public void onSuccess(RetrieveServerLogsResponse response) {
				SC.clearPrompt();
				List<LogRecord> logs = response.getLogRecords();
                for (LogRecord logRecord : logs) {
					// If logging level is ok, publish it
					if (logRecord.getLevel().intValue() >= logger.getLevel().intValue()) {
                        handler.publish(logRecord);
                    }
				}
			}
		};

		// Make the call to the stock price service.
		SmartWPS.getOWSRequestService().retrieveServerLogs(callback);
	}
}
