package uk.ac.glam.smartwps.client.loggerdialog;

/**
 * Presenter for the Logger dialog display for showing client and server-side logs.
 * 
 * @author Jon Britton
 */
public interface LoggerPresenter {
	
	/**
	 * The view interface for the Logger dialog.
	 */
	public interface Display {
		
		/**
		 * Display the dialog on the screen.
		 */
		public void showDialog();
	}
}
