package uk.ac.glam.smartwps.wcs.client.mvp.overview;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import uk.ac.glam.smartwps.base.client.sgwt.SGWTDialogViewWidget;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class CoverageOverviewDialogView extends SGWTDialogViewWidget<CoverageOverviewDialogPresenter> implements
		CoverageOverviewDialogPresenter.Display {

	private HTMLPane coverageInfoPane;

	/**
	 * TODO: document
	 */
	public CoverageOverviewDialogView() {
		super("Coverage Overview", 600, 600);
		addItem(createCoverageOverviewPage());
	}
	
	@Override
	public void setCoverageInfo(String coverageInfo) {
		coverageInfoPane.setContents(coverageInfo);
	}
	
	/**
	 * Creates the page for displaying the coverage details.
	 * 
	 * @param coverage
	 *            coverage details
	 * @return
	 */
	private Canvas createCoverageOverviewPage() {
		VLayout layout = new VLayout();
		layout.setLayoutMargin(5);
		layout.setWidth100();
		layout.setHeight100();

		coverageInfoPane = new HTMLPane();
		coverageInfoPane.setWidth100();
		coverageInfoPane.setHeight100();

		layout.addMember(coverageInfoPane);

		IButton previousButton = new IButton("Previous");
		previousButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			@Override
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				presenter.goToPrevious();
			}
		});

		IButton nextButton = new IButton("Next");
		nextButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			@Override
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				presenter.goToNext();
			}
		});

		HLayout bottomLayout = new HLayout();
		Canvas blankCanvas = new Canvas();
		blankCanvas.setWidth100();
		bottomLayout.addMember(blankCanvas);
		bottomLayout.addMember(previousButton);
		bottomLayout.addMember(nextButton);

		layout.addMember(bottomLayout);

		return layout;
	}
}
