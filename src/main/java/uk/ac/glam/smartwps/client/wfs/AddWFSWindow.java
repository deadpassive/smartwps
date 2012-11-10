package uk.ac.glam.smartwps.client.wfs;


import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * A Window for adding WMS layers to the current SmartWPS session.
 * 
 * @author Jon Britton
 *
 */
public class AddWFSWindow extends Window {

	private WFSLayerSelector layerSelector;

	/**
	 * Creates a new AddMapWindow;
	 */
	public AddWFSWindow() {
		super();

		this.setTitle("Add WFS");
		this.setWidth(800);
		this.setHeight(600);
		this.setShowMinimizeButton(false);
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.centerInPage();
		this.setCanDragResize(true);

		VLayout mainLayout = new VLayout(5);
		mainLayout.setLayoutMargin(5);
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		final DynamicForm urlForm = new DynamicForm();
		urlForm.setNumCols(3);
		urlForm.setWidth100();
		urlForm.setHeight("*");
		urlForm.setColWidths(30, "*", 50);
		
		final ComboBoxItem urlChooser = new ComboBoxItem();
		urlChooser.setTitle("URL");
		urlChooser.setType("comboBox");
		urlChooser.setWidth("100%");
		urlChooser.setDefaultValue("http://localhost:8080/geoserver/ows?service=wfs&version=1.1.0&request=GetCapabilities");
		urlChooser.setValueMap("http://li199-25.members.linode.com:8080/geoserver/ows?service=wfs&version=1.0.0&request=GetCapabilities",
				"http://li199-25.members.linode.com:8080/geoserver/ows?service=wfs&version=1.1.0&request=GetCapabilities",
				"http://localhost:8080/geoserver/ows?service=wfs&version=1.0.0&request=GetCapabilities",
				"http://localhost:8080/geoserver/ows?service=wfs&version=1.1.0&request=GetCapabilities",
				"http://roj.zen.landcareresearch.co.nz:8080/geoserver/ows?service=wfs&version=1.0.0&request=GetCapabilities");
		urlChooser.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				// Detect return key
				if ((event.getCharacterValue() != null) && (event.getCharacterValue() == 13)) {
					layerSelector.loadWFSLayers((String) urlChooser.getValue());
				}
			}
		});

		ButtonItem goButton = new ButtonItem("Go!");
		goButton.setStartRow(false);
		goButton.setWidth("100%");
		goButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				layerSelector.loadWFSLayers((String) urlChooser.getValue());
			}
		});

		urlForm.setItems(urlChooser, goButton);

		mainLayout.addMember(urlForm);

		// Middle layout for process selection
		// HLayout processSelectionLayout = new HLayout();

		layerSelector = new WFSLayerSelector();
		layerSelector.setWidth100();
		layerSelector.setHeight100();
		
		mainLayout.addMember(layerSelector);

		this.addItem(mainLayout);
	}

}
