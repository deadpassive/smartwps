package uk.ac.glam.smartwps.wcs.client.mvp;

import java.util.ArrayList;
import java.util.logging.Logger;

import uk.ac.glam.smartwps.wcs.client.net.WCSRequestService;
import uk.ac.glam.smartwps.wcs.client.net.WCSRequestServiceAsync;
import uk.ac.glam.smartwps.wcs.shared.WCSCapabilitiesResponse;
import uk.ac.glam.smartwps.wcs.shared.WCSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageRecord;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageSummary;
import uk.ac.glam.smartwps.wcs.shared.v111.WCSCapabilities111;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * A list grid for displaying coverages from a given WCS server.
 * 
 * @author jbritton
 *
 */
public class CoverageListGrid extends ListGrid {
	private static final Logger LOGGER = Logger.getLogger("CoverageListGrid");
	private WCSRequestServiceAsync wcsService = GWT.create(WCSRequestService.class);
	
	/**
	 * Creates a new AddCoverageListGrid
	 */
	public CoverageListGrid() {
		ListGridField idField = new ListGridField("identifier", "ID");
		//ListGridField titleField = new ListGridField("title", "Title");
		ListGridField abstractField = new ListGridField("abstract", "Abstract");
		this.setFields(idField, abstractField);
		this.setWrapCells(true);
	}
	
	/**
	 * Loads the coverage details from the given WCS url and displays the results in the listgrid
	 * 
	 * @param url the WCS URL
	 */
	public void loadWCSInfo(String url) {
		LOGGER.info("Loading coverage info from " + url);
		SC.showPrompt("Loading coverage info from " + url);
		
		AsyncCallback<WCSCapabilitiesResponse> callback = new AsyncCallback<WCSCapabilitiesResponse>() {
			@Override
			public void onFailure(Throwable caught) {
				SC.clearPrompt();
				SC.say(caught.getMessage());
			}

			@Override
			public void onSuccess(WCSCapabilitiesResponse result) {
				LOGGER.info("Successfully recieved response");
				SC.clearPrompt();
				LOGGER.info("Capabilities document recieved");
				// Get the capabilities object
				WCSCapabilities111 wcsCaps = result.getWCSCapabilities();
				// Create a CoverageRecord object for each CoverageOfferingBrief
				ArrayList<CoverageSummary> contents = wcsCaps.getContents();
				CoverageRecord[] newRecords = new CoverageRecord[contents.size()];
				for (int i = 0; i < contents.size(); i++) {
					newRecords[i] = new CoverageRecord(contents.get(i));
				}
				// For some reason we have to clear the records first...
				CoverageListGrid.this.setData(new CoverageRecord[0]);
				// Add the new records
				CoverageListGrid.this.setData(newRecords);
				SC.clearPrompt();
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
