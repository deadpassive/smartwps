package uk.ac.glam.smartwps.wcs.client.mvp.select;

import uk.ac.glam.smartwps.base.client.sgwt.SGWTDialogViewWidget;
import uk.ac.glam.smartwps.wcs.client.mvp.CoverageListGrid;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;

import com.google.gwt.user.client.ui.RadioButton;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * This window allows the user to query a WCS server and add a coverage to the
 * map. Once a WCS layer is selected, the user can either connect it to an
 * existing WMS layer for viewing (this is generated automatically for
 * GeoServer) or to download the data to the local WMS where it can be viewed.
 * 
 * The Windows contains a number of separate Canvas objects, each being a
 * different page of the interface.
 * 
 * @author Jon Britton
 * 
 */
public class SelectCoverageDialogViewSGWT extends SGWTDialogViewWidget<SelectCoverageDialogPresenter> implements
		SelectCoverageDialogPresenter.Display {

	private Canvas urlInputPage;
	private Canvas displayMethodPage;
	private Canvas coverageOverviewPage;
	private RadioButton existingWMSRadioButton;
	private RadioButton createWMSRadioButton;
	private CoverageListGrid wcsListGrid;
	private DynamicForm existingWMSForm;
	private DynamicForm createWMSForm;

	/**
	 * Creates a new AddWCSWindow.
	 */
	public SelectCoverageDialogViewSGWT() {
		super("Add WCS", 600, 600);
		
		this.setShowMinimizeButton(false);
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.urlInputPage = createURLInputPage();
		this.addItem(urlInputPage);
	}
	
	@Override
	public String getUrl() {
		return existingWMSForm.getValueAsString("URL");
	}
	
	@Override
	public String getExistingLayer() {
		return existingWMSForm.getValueAsString("LAYER");
	}
	
	@Override
	public String getCreateLayer() {
		return createWMSForm.getValueAsString("LAYER_NAME");
	}
	
	@Override
	public void coverageDetailsRetrieved(CoverageDescription coverageInfo) {
		coverageOverviewPage = createCoverageOverviewPage(coverageInfo);
		removeItem(urlInputPage);
		addItem(coverageOverviewPage);
	}

	/**
	 * Creates the page for entering a WCS url and selecting a coverage.
	 * @return
	 */
	private Canvas createURLInputPage() {
		VLayout layout = new VLayout(5);
		layout.setLayoutMargin(5);
		layout.setWidth100();
		layout.setHeight100();

		final DynamicForm urlForm = new DynamicForm();
		urlForm.setNumCols(3);
		urlForm.setWidth100();
		urlForm.setHeight("*");
		urlForm.setColWidths(30, "*", 50);
		
		final ComboBoxItem urlChooser = new ComboBoxItem();
		urlChooser.setTitle("URL");
		urlChooser.setType("comboBox");
		urlChooser.setWidth("100%");
		urlChooser.setDefaultValue("http://localhost:8080/geoserver/wcs?request=GetCapabilities&version=1.1.1");
		urlChooser.setValueMap("http://localhost:8080/geoserver/wcs?request=GetCapabilities&version=1.1.1");
		urlChooser.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				// Enter key pressed
				if ((event.getCharacterValue() != null) && (event.getCharacterValue() == 13)) {
					wcsListGrid.loadWCSInfo(urlChooser.getValueAsString());
				}
			}
		});

		ButtonItem goButton = new ButtonItem("Go!");
		goButton.setStartRow(false);
		goButton.setWidth("100%");
		goButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				wcsListGrid.loadWCSInfo(urlChooser.getValueAsString());
			}
		});

		urlForm.setItems(urlChooser, goButton);

		layout.addMember(urlForm);

		// ListGrid
		wcsListGrid = new CoverageListGrid();
		wcsListGrid.setHeight100();

		layout.addMember(wcsListGrid);

		final IButton nextButton = new IButton("Next");
		nextButton.disable();
		nextButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.events.ClickEvent event) {
						// Switch to coverage details page
						presenter.retrieveCoverageDetails(wcsListGrid.getSelectedRecord().getCoverageSummary());
					}
				});

		HLayout bottomLayout = new HLayout();
		Canvas blankCanvas = new Canvas();
		blankCanvas.setWidth100();
		bottomLayout.addMember(blankCanvas);
		bottomLayout.addMember(nextButton);

		layout.addMember(bottomLayout);

		wcsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
			@Override
			public void onSelectionChanged(SelectionEvent event) {
				if (event.getSelectedRecord() != null) {
                    nextButton.enable();
                } else {
                    nextButton.disable();
                }
			}
		});

		return layout;
	}

	/**
	 * Creates the page for displaying the coverage details.
	 * 
	 * @param coverage
	 *            coverage details
	 * @return
	 */
	private Canvas createCoverageOverviewPage(CoverageDescription coverage) {
		VLayout layout = new VLayout();
		layout.setLayoutMargin(5);
		layout.setWidth100();
		layout.setHeight100();

		String contents = "<h2>" + coverage.getTitle() + "</h2>";
		contents += "<h3>" + coverage.getIdentifier() + "</h3>";
		contents += "<table>";
		contents += "<tr><td><b>Description: </b></td><td>"
				+ coverage.getAbstract() + "</td></tr>";
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

		HTMLPane htmlPane = new HTMLPane();
		htmlPane.setWidth100();
		htmlPane.setHeight100();
		htmlPane.setContents(contents);

		layout.addMember(htmlPane);

		IButton previousButton = new IButton("Previous");
		previousButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.events.ClickEvent event) {
						removeItem(coverageOverviewPage);
						addItem(urlInputPage);
					}
				});

		IButton nextButton = new IButton("Next");
		nextButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.events.ClickEvent event) {
						removeItem(coverageOverviewPage);
						addItem(createDisplayMethodPage());

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

	/**
	 * Creates the page for choosing how to display the coverage. The user can
	 * choose between an existing WMS layer, or can download the data into a new
	 * WMS layer.
	 * 
	 * @return
	 */
	private Canvas createDisplayMethodPage() {
		VLayout layout = new VLayout();
		layout.setLayoutMargin(5);
		layout.setWidth100();
		layout.setHeight100();

		VLayout displayChooser = new VLayout();
		displayChooser.setLayoutMargin(10);
		displayChooser.setHeight100();
		layout.addMember(displayChooser);

		existingWMSRadioButton = new RadioButton("select", "Existing WMS Layer");
		existingWMSRadioButton.setHeight("20px");
		existingWMSRadioButton.setValue(true);

		existingWMSForm = new DynamicForm();
		existingWMSForm.setPadding(10);

		TextItem urlTextItem = new TextItem();
		urlTextItem.setName("URL");
		urlTextItem.setTitle("URL");
		urlTextItem.setWidth("100%");
		urlTextItem.setValue(presenter.getSelectedCoverage().getServiceURL().replaceAll("wcs",
				"wms"));

		TextItem layerTextItem = new TextItem();
		layerTextItem.setTitle("Layer");
		layerTextItem.setName("LAYER");
		layerTextItem.setValue(presenter.getSelectedCoverage().getIdentifier());

		existingWMSForm.setItems(urlTextItem, layerTextItem);

		existingWMSRadioButton
				.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
					@Override
					public void onClick(
							com.google.gwt.event.dom.client.ClickEvent event) {
						updateDisplayMethodForms();
					}
				});

		displayChooser.addMember(existingWMSRadioButton);
		displayChooser.addMember(existingWMSForm);

		createWMSRadioButton = new RadioButton("select", "Create WMS Layer");
		createWMSRadioButton.setHeight("25px");

		createWMSForm = new DynamicForm();
		createWMSForm.setPadding(10);
		createWMSForm.disable();

		TextItem layerName = new TextItem();
		layerName.setTitle("Name");
		layerName.setWidth("100%");
		// TODO: make sure string is valid format for a filename
		layerName.setValue(presenter.getSelectedCoverage().getIdentifier().replaceAll(":", ""));
		layerName.setName("LAYER_NAME");

		createWMSForm.setItems(layerName);

		createWMSRadioButton
				.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
					@Override
					public void onClick(
							com.google.gwt.event.dom.client.ClickEvent event) {
						updateDisplayMethodForms();
					}
				});
		displayChooser.addMember(createWMSRadioButton);
		displayChooser.addMember(createWMSForm);

		IButton previousButton = new IButton("Previous");
		previousButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.events.ClickEvent event) {
						// Switch to display method page
						removeItem(displayMethodPage);
						addItem(coverageOverviewPage);
					}
				});

		IButton nextButton = new IButton("Next");
		nextButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					@Override
					public void onClick(
							com.smartgwt.client.widgets.events.ClickEvent event) {
						presenter.doNext();
					}
				});

		HLayout bottomLayout = new HLayout();
		Canvas blankCanvas = new Canvas();
		blankCanvas.setWidth100();
		bottomLayout.addMember(blankCanvas);
		bottomLayout.addMember(previousButton);
		bottomLayout.addMember(nextButton);

		layout.addMember(bottomLayout);

		displayMethodPage = layout;

		return layout;
	}

	@Override
	public void resetWindow() {
		// remove current items
		Canvas[] items = getItems();
		for (int i = 0; i < items.length; i++) {
			this.removeItem(getItems()[i]);
		}
		addItem(urlInputPage);
	}

	private void updateDisplayMethodForms() {
		if (createWMSRadioButton.getValue() == true) {
			createWMSForm.enable();
			existingWMSForm.disable();
			presenter.setExistingWMSLayer(false);
		} else {
			createWMSForm.disable();
			existingWMSForm.enable();
			presenter.setExistingWMSLayer(true);
		}
	}
}
