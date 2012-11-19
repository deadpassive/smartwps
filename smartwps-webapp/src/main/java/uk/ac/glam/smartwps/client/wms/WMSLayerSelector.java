package uk.ac.glam.smartwps.client.wms;

import uk.ac.glam.smartwps.client.DataSourceManager;
import uk.ac.glam.smartwps.client.datatree.DataTree;
import uk.ac.glam.smartwps.client.net.WMSRequestService;
import uk.ac.glam.smartwps.client.net.WMSRequestServiceAsync;
import uk.ac.glam.smartwps.shared.DataSource;
import uk.ac.glam.smartwps.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WMSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import java.util.List;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WMSLayerSelector extends HLayout {

	private ListGrid layerList;
	private WMSRequestServiceAsync wmsService = GWT.create(WMSRequestService.class);
	private final DataTree dataTree;
	
	/**
	 * TODO: document
	 */
	public WMSLayerSelector(final DataTree dataTree) {
		this.dataTree = dataTree;
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
			            	// TODO: must refactor so that DataTree doesn't know about layer types!!
			            	WMSLayerSelector.this.dataTree.addWMSLayer(((WMSLayerRecord)record).getWMSLayer());
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
	 * @param url
	 */
	public void loadWMSLayers(String url) {
		SC.showPrompt("Contacting WMS server at " + url);

		// Set up the callback object.
		AsyncCallback<WMSGetCapabilitiesResponse> callback = new AsyncCallback<WMSGetCapabilitiesResponse>() {
			@Override
			public void onFailure(Throwable caught) {
				SC.clearPrompt();
				SC.say(caught.getMessage());
			}

			@Override
			public void onSuccess(WMSGetCapabilitiesResponse result) {
				GWT.log("SUCCESS");
				SC.clearPrompt();
				List<WMSLayer> wmsLayers = result.getWMSLayers();
				
				// Register DataSource (all layers should have the same DataSource)
				DataSource ds = wmsLayers.get(0).getDataSource();
				DataSourceManager.registerDataSource(ds.getUrl(), ds);
				
				WMSLayerRecord[] newRecords = new WMSLayerRecord[wmsLayers.size()];
				for (int i = 0; i < wmsLayers.size(); i++) {
					newRecords[i] = new WMSLayerRecord(wmsLayers.get(i));
				}
				// For some reason we have to clear the records first...
				layerList.setData(new WMSLayerRecord[0]);
				layerList.setData(newRecords);
			}
		};

		// Make the call to the stock price service.
		GWT.log("Making service call");
		WMSGetCapabilitiesRequest request = new WMSGetCapabilitiesRequest(url);
		wmsService.wmsGetCapabilities(request, callback);
	}
}
