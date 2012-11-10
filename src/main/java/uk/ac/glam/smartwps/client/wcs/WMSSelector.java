package uk.ac.glam.smartwps.client.wcs;

import java.util.ArrayList;
import java.util.Iterator;

import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.shared.wcs111.CoverageDescription;
import uk.ac.glam.smartwps.shared.wcs111.WCSCoverage;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

/**
 * This window is used when there are more than one WMS layers which could possibly represent
 * a WCS coverage.
 * 
 * @author Jon Britton
 *
 */
public class WMSSelector extends Window {

	/**
	 * Create a new WMSSelector window.
	 * 
	 * @param wmsLayers
	 * @param coverage
	 */
	public WMSSelector(final ArrayList<WMSLayer> wmsLayers, final CoverageDescription coverage) {
		this.setIsModal(true);
		this.setShowMinimizeButton(false);
		this.setShowCloseButton(false);
		this.setWidth(200);
		this.setHeight(100);
		this.centerInPage();
		
		final DynamicForm form = new DynamicForm();
		final ComboBoxItem comboBox = new ComboBoxItem();
		comboBox.setTitle("WMS Layer");
		String[] names = new String[wmsLayers.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = wmsLayers.get(i).getName();
		}
		comboBox.setValueMap(names);
		
		ButtonItem okButton = new ButtonItem("OK");
		okButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//Get selection
				WMSLayer selectedLayer = null;
				for (Iterator<WMSLayer> iterator = wmsLayers.iterator(); iterator.hasNext();) {
					WMSLayer wmsLayer = iterator.next();
					if (wmsLayer.getName().equals(comboBox.getValue())) {
						selectedLayer = wmsLayer;
						break;
					}
				}
				if (selectedLayer != null) {
					WCSCoverage wcsCoverage = new WCSCoverage(coverage, selectedLayer);
					SmartWPS.getSmartWPS().getDataTree().addWCSCoverage(wcsCoverage);
					WMSSelector.this.hide();
				}
			}
		});
		
		form.setItems(comboBox, okButton);
		
		this.addItem(form);
	}
}
