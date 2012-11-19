package uk.ac.glam.smartwps.client.logging;

import com.smartgwt.client.widgets.Window;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class LoggerWindow extends Window {
	
	private LogViewer logViewer = new LogViewer();

	/**
	 * TODO: document
	 */
	public LoggerWindow() {
		this.setTitle("Logger");
		this.setWidth(600);
		this.setHeight(400);
		this.setShowModalMask(true);
		this.setCanDragResize(true);
		
		this.addItem(logViewer);
	}
}
