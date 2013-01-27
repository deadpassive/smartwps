package uk.ac.glam.smartwps.wcs.client;

import uk.ac.glam.smartwps.base.client.event.AddLayersEvent;
import uk.ac.glam.smartwps.base.shared.Data;
import uk.ac.glam.smartwps.wcs.shared.WCSCoverage;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;
import uk.ac.glam.smartwps.wms.shared.WMSLayer;

import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

import java.util.HashSet;
import java.util.List;

/**
 * This window is used when there are more than one WMS layers which could possibly represent a WCS coverage.
 *
 * @author Jon Britton
 */
public class WMSSelector extends Window {

    /**
     * Create a new WMSSelector window.
     * @param eventBus 
     * @param wmsLayers
     * @param coverage
     */
    public WMSSelector(final EventBus eventBus, final List<WMSLayer> wmsLayers, final CoverageDescription coverage) {
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
                for (WMSLayer wmsLayer : wmsLayers) {
                    if (wmsLayer.getName().equals(comboBox.getValue())) {
                        selectedLayer = wmsLayer;
                        break;
                    }
                }
                if (selectedLayer != null) {
                    WCSCoverage wcsCoverage = new WCSCoverage(coverage, selectedLayer);
                    HashSet<Data> layers = new HashSet<Data>(1);
                    layers.add(wcsCoverage);
                    eventBus.fireEvent(new AddLayersEvent(layers));
                    WMSSelector.this.hide();
                }
            }
        });

        form.setItems(comboBox, okButton);

        this.addItem(form);
    }
}
