package uk.ac.glam.smartwps.wcs.client.mvp.select;

import uk.ac.glam.smartwps.base.client.mvp.DialogView;
import uk.ac.glam.smartwps.base.client.mvp.Presenter;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageSummary;

public interface SelectCoverageDialogPresenter extends Presenter<SelectCoverageDialogPresenter.Display> {
	
	public static final String PLACE_NAME = "SelectCoverage";
	
	public interface Display extends DialogView<SelectCoverageDialogPresenter> {

		String getUrl();
		
		String getExistingLayer();
		
		void resetWindow();

		String getCreateLayer();
	}

	/**
	 * TODO: document
	 */
	void doNext();

	/**
	 * TODO: document
	 * @param coverageSummary
	 */
	void retrieveCoverageDetails(CoverageSummary coverageSummary);
	
	/**
	 * TODO: document
	 * @return
	 */
	CoverageDescription getSelectedCoverage();
	
	void setExistingWMSLayer(boolean existing);

}
