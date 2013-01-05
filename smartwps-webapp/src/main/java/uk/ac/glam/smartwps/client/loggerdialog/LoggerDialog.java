package uk.ac.glam.smartwps.client.loggerdialog;


import com.smartgwt.client.widgets.Window;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class LoggerDialog extends Window implements LoggerPresenter.Display {
	
	private LogViewer logViewer = new LogViewer();

	/**
	 * TODO: document
	 */
	public LoggerDialog() {
		super();
		this.setTitle("Logger");
		this.setWidth(600);
		this.setHeight(400);
		this.setShowModalMask(true);
		this.setCanDragResize(true);
		
		this.addItem(logViewer);
	}

	@Override
	public void showDialog() {
		centerInPage();
		show();
	}
}
