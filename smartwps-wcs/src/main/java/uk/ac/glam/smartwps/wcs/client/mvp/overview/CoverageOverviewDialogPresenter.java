package uk.ac.glam.smartwps.wcs.client.mvp.overview;

import uk.ac.glam.smartwps.base.client.mvp.DialogView;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public interface CoverageOverviewDialogPresenter {

	/**
	 * TODO: document
	 * 
	 * @author Jon Britton
	 */
	public interface Display extends DialogView<CoverageOverviewDialogPresenter> {
		public void setCoverageInfo(String coverageInfo);
	}

	void goToPrevious();

	void goToNext();

	void setCoverageInfo(CoverageDescription selectedCoverage);
}
