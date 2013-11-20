package uk.ac.glam.smartwps.wcs.client.mvp.overview;

import uk.ac.glam.smartwps.base.client.sgwt.SGWTDialogViewWidget;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class CoverageOverviewDialogView extends SGWTDialogViewWidget<CoverageOverviewDialogPresenter> implements
		CoverageOverviewDialogPresenter.Display {

	/**
	 * TODO: document
	 */
	public CoverageOverviewDialogView() {
		super("Coverage Overview", 600, 600);
	}
}
