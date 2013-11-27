package uk.ac.glam.smartwps.wcs.client.mvp.overview;

import uk.ac.glam.smartwps.base.client.event.PlaceRequestEvent;
import uk.ac.glam.smartwps.base.client.mvp.PresenterBase;
import uk.ac.glam.smartwps.wcs.client.mvp.select.SelectCoverageDialogPresenter;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;

import com.google.web.bindery.event.shared.EventBus;

public class CoverageOverviewPresenterImpl extends PresenterBase<CoverageOverviewDialogPresenter.Display> implements CoverageOverviewDialogPresenter {

	private CoverageDescription selectedCoverage;

	public CoverageOverviewPresenterImpl(EventBus eventBus, CoverageOverviewDialogPresenter.Display display) {
		super(eventBus, display, "CoverageOverview");
	}
	
	@Override
	public void goToPrevious() {
		eventBus.fireEvent(new PlaceRequestEvent(SelectCoverageDialogPresenter.PLACE_NAME));
	}

	@Override
	public void goToNext() {
		// TODO: fire event to go to method page
	}

	@Override
	public void setCoverageInfo(CoverageDescription selectedCoverage) {
		this.selectedCoverage = selectedCoverage;
	}
	
	@Override
	protected void showView() {
		super.showView();
		if (selectedCoverage == null) {
			view.setCoverageInfo("");
		} else {
			String contents = "<h2>" + selectedCoverage.getTitle() + "</h2>";
			contents += "<h3>" + selectedCoverage.getIdentifier() + "</h3>";
			contents += "<table>";
			contents += "<tr><td><b>Description: </b></td><td>"
					+ selectedCoverage.getAbstract() + "</td></tr>";
			// LonLatEnvelope
					//contents += "<tr><td><b>LonLatEnvelope: </b></td><td>("
					//		+ coverage.getLonLatEnvelope().getMinX() + ", "
					//		+ coverage.getLonLatEnvelope().getMinY() + "), ("
					//		+ coverage.getLonLatEnvelope().getMaxX() + ", "
					//		+ coverage.getLonLatEnvelope().getMaxY() + ") ["
					//		+ coverage.getLonLatEnvelope().getProjection() + "]</td><tr>";
					// Request CRSs
					//contents += "<tr><td><b>Request CRSs: </b></td><td>"
					//		+ coverage.getRequestCRSs() + "</td></tr>";
					// Response CRSs
					//contents += "<tr><td><b>Response CRSs: </b></td><td>"
					//		+ coverage.getResponseCRSs() + "</td></tr>";
			contents += "</table>";
			view.setCoverageInfo(contents);
		}
	}

}
