package uk.ac.glam.smartwps.client.addwmsdialog;

import uk.ac.glam.smartwps.wms.shared.WMSLayer;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * Widget for selecting WMS layers.
 * 
 * @author Jon Britton
 * @deprecated
 */
public class WMSLayerSelector extends HLayout {

	private final ListGrid layerList;
	
	/**
	 * TODO: document
	 * @param callback 
	 */
	public WMSLayerSelector(final AddLayerCallback<WMSLayer> callback) {
		layerList = new ListGrid() {  
			
			@Override
			protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
				String fieldName = this.getFieldName(colNum);  
		        if (fieldName.equals("addButton")) {
					final IButton button = new IButton();  
			        button.setHeight(18);  
			        button.setWidth(50);                      
			        button.setTitle("Add");  
			        button.addClickHandler(new ClickHandler() {  
			            @Override
						public void onClick(ClickEvent event) {
			            	callback.addLayer(((SmartWMSLayerRecord)record).getWMSLayer());
			            } 
			        });  
			        return button;
		        }
		        
		        return null;
			}
			
        };
        layerList.setShowRecordComponents(true);          
        layerList.setShowRecordComponentsByCell(true);
 
		ListGridField idField = new ListGridField("name", "Name");
		ListGridField titleField = new ListGridField("title", "Title");
		ListGridField addField = new ListGridField("addButton", "Add");
		addField.setWidth(50);
		layerList.setFields(idField, titleField, addField);
		
		layerList.setWidth("*");
		layerList.setHeight100();
		
		this.addMember(layerList);
	}
	
	/**
	 * TODO: document
	 * @param records
	 */
	public void setData(SmartWMSLayerRecord[] records) {
		// For some reason we have to clear the records first...
		layerList.setData(new SmartWMSLayerRecord[0]);
		layerList.setData(records);
	}
}
