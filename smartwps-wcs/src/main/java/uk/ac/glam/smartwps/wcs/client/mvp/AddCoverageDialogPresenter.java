package uk.ac.glam.smartwps.wcs.client.mvp;

import uk.ac.glam.smartwps.base.client.mvp.DialogDisplay;

public interface AddCoverageDialogPresenter {
	
	public interface Display extends DialogDisplay<AddCoverageDialogPresenter> {
		String getUrl();
		
		String getLayer();
		
		void resetWindow();
		
		void hideDialog();
	}

	/**
	 * TODO: document
	 */
	void doNext();

}
