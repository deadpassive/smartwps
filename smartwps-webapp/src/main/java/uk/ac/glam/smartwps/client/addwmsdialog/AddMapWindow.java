package uk.ac.glam.smartwps.client.addwmsdialog;

import java.util.logging.Logger;

import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.client.wms.WMSLayerSelector;


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
 */
public class AddMapWindow extends Window {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.client");

	private WMSLayerSelector layerSelector;

	/**
	 * Creates a new AddMapWindow;
	 */
	public AddMapWindow() {
		super();

		LOGGER.info("Creating AddMapWindow");
		this.setTitle("Add WMS");
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
		urlChooser.setDefaultValue("http://li199-25.members.linode.com:8080/geoserver/wms");
		urlChooser.setValueMap("http://li199-25.members.linode.com:8080/geoserver/wms");
		urlChooser.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				// Enter key pressed
				if ((event.getCharacterValue() != null) && (event.getCharacterValue() == 13)) {
					layerSelector.loadWMSLayers((String) urlChooser.getValue());
				}
			}
		});

		ButtonItem goButton = new ButtonItem("Go!");
		goButton.setStartRow(false);
		goButton.setWidth("100%");
		goButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				layerSelector.loadWMSLayers((String) urlChooser.getValue());
			}
		});

		urlForm.setItems(urlChooser, goButton);

		mainLayout.addMember(urlForm);

		layerSelector = new WMSLayerSelector(SmartWPS.getSmartWPS().getDataTree());
		layerSelector.setWidth100();
		layerSelector.setHeight100();
		
		mainLayout.addMember(layerSelector);

		this.addItem(mainLayout);
	}

}
