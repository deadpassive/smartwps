package uk.ac.glam.smartwps.wcs.client.mvp.select;

import uk.ac.glam.smartwps.base.client.mvp.DialogDisplay;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageSummary;

public interface SelectCoverageDialogPresenter {
	
	public interface Display extends DialogDisplay<SelectCoverageDialogPresenter> {

		String getUrl();
		
		String getExistingLayer();
		
		void resetWindow();
		
		void coverageDetailsRetrieved(CoverageDescription coverageInfo);

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
