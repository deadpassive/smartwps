package uk.ac.glam.smartwps.client.wps;

import uk.ac.glam.smartwps.wps.client.net.WPSRequestServiceAsync;

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
 * A Window for adding WPS processes to the current SmartWPS session.
 * 
 * @author Jon Britton
 */
public class AddProcessWindow extends Window {

	private AddProcessListGrid processList;

	/**
	 * Creates a new AddProcessWindow.
	 * @param wpsService 
	 */
	public AddProcessWindow(WPSRequestServiceAsync wpsService) {
		super();

		this.setTitle("Add WPS");
		this.setWidth(600);
		this.setHeight(400);
		this.setShowMinimizeButton(new Boolean(false));
		this.setIsModal(new Boolean(true));
		this.setShowModalMask(new Boolean(true));
		this.centerInPage();
		this.setCanDragResize(new Boolean(true));

		VLayout mainLayout = new VLayout(5);
		mainLayout.setLayoutMargin(new Integer(5));
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
		urlChooser.setDefaultValue("http://localhost:8080/wps/WebProcessingService");
		urlChooser.setValueMap("http://li199-25.members.linode.com:8080/wps/WebProcessingService?Request=GetCapabilities&Service=WPS&Version=1.0.0",
				"http://geoprocessing.demo.52north.org:8080/wps/WebProcessingService?REQUEST=GetCapabilities&SERVICE=WPS&VERSION=1.0.0",
				"http://localhost:8080/wps/WebProcessingService");
		urlChooser.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				// Enter key pressed
				if (event.getCharacterValue() == 13) {
					processList.loadProcessInfo((String) urlChooser.getValue());
				}
			}
		});

		ButtonItem goButton = new ButtonItem("Go!");
		goButton.setStartRow(false);
		goButton.setWidth("100%");
		goButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				processList.loadProcessInfo((String) urlChooser.getValue());
			}
		});

		urlForm.setItems(urlChooser, goButton);

		mainLayout.addMember(urlForm);

		processList = new AddProcessListGrid(wpsService);
		processList.setWidth100();
		processList.setHeight100();
		
		mainLayout.addMember(processList);

		this.addItem(mainLayout);
	}

}
