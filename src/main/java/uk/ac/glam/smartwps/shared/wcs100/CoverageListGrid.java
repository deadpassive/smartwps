package uk.ac.glam.smartwps.shared.wcs100;

import java.util.logging.Logger;

import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.client.net.WCSRequestService;
import uk.ac.glam.smartwps.client.net.WCSRequestServiceAsync;
import uk.ac.glam.smartwps.shared.request.WCSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WCSCapabilitiesResponse;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import java.util.logging.Level;

/**
 * A list grid for displaying coverages from a given WCS server.
 * 
 * @author jbritton
 */
public class CoverageListGrid extends ListGrid {
	private static final Logger LOGGER = SmartWPS.LOGGER;
	private static final WCSRequestServiceAsync wcsService = GWT.create(WCSRequestService.class);
	
	/**
	 * Creates a new AddCoverageListGrid
	 */
	public CoverageListGrid() {
		ListGridField nameField = new ListGridField("name", "Name");
		ListGridField labelField = new ListGridField("label", "Label");
		ListGridField descriptionField = new ListGridField("description", "Description");
		this.setFields(nameField, labelField, descriptionField);
		this.setWrapCells(true);
	}
	
	/**
	 * Loads the coverage details from the given WCS url and displays the results in the listgrid
	 * 
	 * @param url the WCS URL
	 */
	public void loadWCSInfo(String url) {
		LOGGER.log(Level.INFO, "Loading coverage info from {0}", url);
		SC.showPrompt("Loading coverage info from " + url);
		
		AsyncCallback<WCSCapabilitiesResponse> callback = new AsyncCallback<WCSCapabilitiesResponse>() {
            @Override
			public void onFailure(Throwable caught) {
				SC.clearPrompt();
				SC.say(caught.getMessage());
			}

            @Override
			public void onSuccess(WCSCapabilitiesResponse result) {
//				LOGGER.info("Successfully recieved response");
//				SC.clearPrompt();
//				LOGGER.info("Capabilities document recieved");
//				// Get the capabilities object
//				WCSCapabilities wcsCaps = result.getWCSCapabilities();
//				// Create a CoverageRecord object for each CoverageOfferingBrief
//				ArrayList<CoverageOfferingBrief> coverageOfferings = wcsCaps.getCoverageOfferings();
//				CoverageRecord[] newRecords = new CoverageRecord[coverageOfferings.size()];
//				for (int i = 0; i < coverageOfferings.size(); i++) {
//					newRecords[i] = new CoverageRecord(coverageOfferings.get(i));
//				}
//				// For some reason we have to clear the records first...
//				CoverageListGrid.this.setData(new ProcessRecord[0]);
//				// Add the new records
//				CoverageListGrid.this.setData(newRecords);
//				SC.clearPrompt();
			}
		};

		// Make the call to the stock price service.
		LOGGER.info("Making RPC");
		wcsService.wcsGetCapabilities(new WCSGetCapabilitiesRequest(url), callback);
	}

    @Override
	public CoverageRecord getSelectedRecord() {
		return (CoverageRecord) super.getSelectedRecord();
	}	
}
