package uk.ac.glam.smartwps.wms.client.addwmsdialog;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import uk.ac.glam.smartwps.wms.shared.WMSLayer;

import com.smartgwt.client.util.SC;
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
 * @deprecated use GWT implementation instead
 */
@Deprecated
public class SmartAddWMSDialog extends Window implements AddWMSPresenter.Display {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.client");

	private WMSLayerSelector layerSelector;

	private AddWMSPresenter presenter;

	/**
	 * Creates a new AddMapWindow.
	 * 
	 * TODO: remove dependency on event bus
	 */
	public SmartAddWMSDialog() {
		super();

		LOGGER.info("Creating AddMapWindow");
		this.setTitle("Add WMS");
		this.setWidth(800);
		this.setHeight(600);
		this.setShowMinimizeButton(false);
		this.setIsModal(true);
		this.setShowModalMask(true);
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
		urlChooser.setDefaultValue("http://localhost:8080/geoserver/wms");
		urlChooser.setValueMap("http://localhost:8080/geoserver/wms");
		urlChooser.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				// Enter key pressed
				if ((event.getCharacterValue() != null) && (event.getCharacterValue() == 13)) {
					sendRequest(urlChooser.getValueAsString());
				}
			}
		});

		ButtonItem goButton = new ButtonItem("Go!");
		goButton.setStartRow(false);
		goButton.setWidth("100%");
		goButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendRequest(urlChooser.getValueAsString());
			}
		});

		urlForm.setItems(urlChooser, goButton);

		mainLayout.addMember(urlForm);

		layerSelector = new WMSLayerSelector(new AddLayerCallback<WMSLayer>() {
			@Override
			public void addLayer(WMSLayer wmsLayer) {
				HashSet<WMSLayer> layers = new HashSet<WMSLayer>(1);
				layers.add(wmsLayer);
				presenter.addLayers(layers);
			}
		});
		layerSelector.setWidth100();
		layerSelector.setHeight100();
		
		mainLayout.addMember(layerSelector);

		this.addItem(mainLayout);
	}
	
	private void sendRequest(String url) {
		SC.showPrompt("Contacting WMS server at " + url);
		presenter.retrieveWMSLayer(url);
	}
	
	@Override
	public void showDialog() {
		centerInPage();
		show();
	}

	@Override
	public void setPresenter(AddWMSPresenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void setWMSLayers(List<WMSLayer> wmsLayers) {
		SC.clearPrompt();
		SmartWMSLayerRecord[] newRecords = new SmartWMSLayerRecord[wmsLayers.size()];
		for (int i = 0; i < wmsLayers.size(); i++) {
			newRecords[i] = new SmartWMSLayerRecord(wmsLayers.get(i));
		}
		layerSelector.setData(newRecords);
	}
	
	@Override
	public void doFailure(String message) {
		SC.clearPrompt();
		SC.say(message);
	}
}
