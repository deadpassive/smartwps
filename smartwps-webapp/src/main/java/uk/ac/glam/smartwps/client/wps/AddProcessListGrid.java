package uk.ac.glam.smartwps.client.wps;

import java.util.ArrayList;
import java.util.logging.Logger;

import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.client.net.WPSRequestService;
import uk.ac.glam.smartwps.client.net.WPSRequestServiceAsync;
import uk.ac.glam.smartwps.shared.request.WPSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WPSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wps.DetailedProcessDescriptor;
import uk.ac.glam.smartwps.shared.wps.ProcessDescriptor;

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

/**
 * A list grid for adding a process to the current SmartWPS session.
 * 
 * @author Jon Britton
 */
public class AddProcessListGrid extends ListGrid {

	private static final Logger LOGGER = Logger.getLogger("AddProcessListGrid");
	private WPSRequestServiceAsync wpsService = GWT.create(WPSRequestService.class);
	
	/**
	 * Creates a new AddProcessListGrid.
	 * @param wpsService 
	 */
	public AddProcessListGrid(WPSRequestServiceAsync wpsService) {
		this.wpsService = wpsService;
		ListGridField idField = new ListGridField("id", "ID");
		ListGridField titleField = new ListGridField("title", "Title");
		ListGridField addField = new ListGridField("addButton", "Add");
		addField.setWidth(50);
		this.setCellHeight(30);
		this.setFields(idField, titleField, addField);
		this.setShowRecordComponents(new Boolean(true));          
        this.setShowRecordComponentsByCell(new Boolean(true));  
	}
	
	/**
	 * Load the process metadata from the given WPS URL.
	 * @param url The URL to the WPS.
	 */
	public void loadProcessInfo(String url) {

		SC.showPrompt("Loading process info from server...");
		LOGGER.info("Loading process info from server...");
		
		// Set up the callback object.
		AsyncCallback<WPSGetCapabilitiesResponse> callback = new AsyncCallback<WPSGetCapabilitiesResponse>() {
			@Override
			public void onFailure(Throwable caught) {
				SC.clearPrompt();
				SC.say(caught.getMessage());
			}

			@Override
			public void onSuccess(WPSGetCapabilitiesResponse result) {
				LOGGER.info("Successfully executed RPC request");
				SC.clearPrompt();
				
				ArrayList<ProcessDescriptor> processes = result.getProcessDescriptors();
				ProcessRecord[] newRecords = new ProcessRecord[processes.size()];
				for (int i = 0; i < processes.size(); i++) {
					newRecords[i] = new ProcessRecord(processes.get(i));
				}
				// For some reason we have to clear the records first...
				AddProcessListGrid.this.setData(new ProcessRecord[0]);
				AddProcessListGrid.this.setData(newRecords);
			}
		};

		// Make the call to the stock price service.
		LOGGER.info("Making service call");
		wpsService.wpsGetCapabilities(new WPSGetCapabilitiesRequest(url), callback);
	}

	@Override
	protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
		String fieldName = this.getFieldName(colNum.intValue());  
        if (fieldName.equals("addButton")) {
			final IButton button = new IButton();  
	        button.setHeight(18);  
	        button.setWidth(50);                      
	        button.setTitle("Add");  
	        button.addClickHandler(new ClickHandler() {  
	            @Override
				public void onClick(ClickEvent event) {
	            	// Get detailed descriptor for server
	            	SC.showPrompt("Loading detailed process info from server for " + record.getAttribute("id"));
	        		LOGGER.info("Loading detailed process info from server for " + record.getAttribute("id"));
	        		
	        		// Set up the callback object.
	        		AsyncCallback<DetailedProcessDescriptor> callback = new AsyncCallback<DetailedProcessDescriptor>() {
	        			@Override
						public void onFailure(Throwable caught) {
	        				SC.clearPrompt();
	        				SC.say(caught.getMessage());
	        			}

						@Override
						public void onSuccess(DetailedProcessDescriptor result) {
							ProcessRecord newRecord = new ProcessRecord(result);
							SmartWPS.getSmartWPS().getRunProcessList().addData(newRecord);
			                button.setDisabled(true);
			                SC.clearPrompt();
						}
	        		};

	        		// Make the call to the stock price service.
	        		LOGGER.info("Making service call");
	        		ProcessRecord pRecord = (ProcessRecord)record;
	        		ProcessDescriptor pd = pRecord.getProcessDescriptor();
	        		wpsService.getProcessDetails(pd.getServiceURL(), pd.getId(), callback);
	            }  
	        });  
	        return button;
        }
        return null;
	}
}
