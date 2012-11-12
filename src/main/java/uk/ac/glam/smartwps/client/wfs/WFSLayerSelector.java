package uk.ac.glam.smartwps.client.wfs;


import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.client.net.WFSRequestService;
import uk.ac.glam.smartwps.client.net.WFSRequestServiceAsync;
import uk.ac.glam.smartwps.shared.request.WFSDescribeFeatureTypeRequest;
import uk.ac.glam.smartwps.shared.request.WFSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WFSDescribeFeatureTypeResponse;
import uk.ac.glam.smartwps.shared.response.WFSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;
import uk.ac.glam.smartwps.shared.wfs.WFSFeatureTypeBase;

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
public class WFSLayerSelector extends HLayout {

	private final static WFSRequestServiceAsync WFS_RPC_SERVICE = GWT.create(WFSRequestService.class);
	private ListGrid layerList;
	
	/**
	 * TODO: document
	 */
	public WFSLayerSelector() {
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
			            	SC.showPrompt("Retrieving layer details...");

			        		// Set up the callback object.
			        		AsyncCallback<WFSDescribeFeatureTypeResponse> callback = new AsyncCallback<WFSDescribeFeatureTypeResponse>() {
			        			@Override
								public void onFailure(Throwable caught) {
			        				caught.printStackTrace();
			        				SC.say("Failed to contact server");
			        				SC.clearPrompt();
			        			}

			        			@Override
								public void onSuccess(WFSDescribeFeatureTypeResponse result) {
			        				GWT.log("SUCCESS");
			        				WFSFeatureType featureType = result.getFeatureType();
					            	SmartWPS.getSmartWPS().getDataTree().addWFSFeature(featureType);
					            	SC.clearPrompt();
			        			}
			        		};

			        		// Make the call to the stock price service.
			        		GWT.log("Making service call");
			        		WFSFeatureTypeBase featureTypeBase = ((WFSLayerRecord)record).getWFSFeatureType();
			        		WFSDescribeFeatureTypeRequest request = new WFSDescribeFeatureTypeRequest(featureTypeBase);
			        		WFS_RPC_SERVICE.wfsDescribeFeatureType(request, callback);
			            } 
			        });  
			        return button;
		        }
		        
		        return null;
			}
			
        };
        layerList.setShowRecordComponents(true);          
        layerList.setShowRecordComponentsByCell(true);
 
		ListGridField idField = new ListGridField("typename", "TypeName");
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
	public void loadWFSLayers(String url) {
		SC.showPrompt("Contacting WFS server at " + url);

		// Set up the callback object.
		AsyncCallback<WFSGetCapabilitiesResponse> callback = new AsyncCallback<WFSGetCapabilitiesResponse>() {
			@Override
			public void onFailure(Throwable caught) {
				SC.clearPrompt();
				SC.say(caught.getMessage());
			}

			@Override
			public void onSuccess(WFSGetCapabilitiesResponse result) {
				SC.clearPrompt();
				List<WFSFeatureTypeBase> wfsLayers = result.getWFSLayers();
				WFSLayerRecord[] newRecords = new WFSLayerRecord[wfsLayers.size()];
				for (int i = 0; i < wfsLayers.size(); i++) {
					newRecords[i] = new WFSLayerRecord(wfsLayers.get(i));
				}
				// For some reason we have to clear the records first...
				layerList.setData(new WFSLayerRecord[0]);
				layerList.setData(newRecords);
				
			}
		};

		// Make the call to the stock price service.
		GWT.log("Making service call");
		WFS_RPC_SERVICE.wfsGetCapabilities(new WFSGetCapabilitiesRequest(url), callback);
	}
}
