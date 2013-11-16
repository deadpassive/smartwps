package uk.ac.glam.smartwps.wcs.client.mvp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

import uk.ac.glam.smartwps.base.client.event.AddLayersEvent;
import uk.ac.glam.smartwps.base.shared.Data;
import uk.ac.glam.smartwps.wcs.client.AddCoverageWindow;
import uk.ac.glam.smartwps.wcs.client.WMSSelector;
import uk.ac.glam.smartwps.wcs.client.event.ShowWCSDialogEvent;
import uk.ac.glam.smartwps.wcs.client.event.ShowWCSDialogHandler;
import uk.ac.glam.smartwps.wcs.client.net.WCSRequestServiceAsync;
import uk.ac.glam.smartwps.wcs.shared.WCSGetCoverageAndStoreRequest;
import uk.ac.glam.smartwps.wcs.shared.WCSGetCoverageAndStoreResponse;
import uk.ac.glam.smartwps.wcs.shared.v111.CoverageDescription;
import uk.ac.glam.smartwps.wms.client.net.WMSRequestServiceAsync;
import uk.ac.glam.smartwps.wms.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wms.shared.response.WMSGetCapabilitiesResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.util.SC;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class AddCoverageDialogPresenterImpl implements AddCoverageDialogPresenter {
	
	private static final Logger logger = Logger.getLogger("AddCoverageDialogPresenter");
	
	private final EventBus eventBus;
	private final Display display;
	private final WCSRequestServiceAsync wcsService;
	private final WMSRequestServiceAsync wmsService;
	
	private boolean existingWMSLayer = true;
	private CoverageDescription selectedCoverage;

	

	

	/**
	 * TODO: document
	 * @param eventBus
	 * @param display
	 */
	public AddCoverageDialogPresenterImpl(EventBus eventBus, final AddCoverageDialogPresenter.Display display, WMSRequestServiceAsync wmsService, 
			WCSRequestServiceAsync wcsService) {
		this.eventBus = eventBus;
		this.display = display;
		this.wcsService = wcsService;
		this.wmsService = wmsService;
		
		eventBus.addHandler(ShowWCSDialogEvent.TYPE, new ShowWCSDialogHandler() {
			
			@Override
			public void onShowDialog(ShowWCSDialogEvent event) {
				display.showDialog();
			}
		});
	}

	@Override
	public void doNext() {
		if (existingWMSLayer) {
			String serviceURL = display.getUrl();
			String layer = display.getLayer();
			ArrayList<String> layerList = new ArrayList<String>();
			layerList.add(layer);
			SC.showPrompt("Contacting WMS server at "
					+ serviceURL);

			// Set up the callback object.
			AsyncCallback<WMSGetCapabilitiesResponse> callback = new AsyncCallback<WMSGetCapabilitiesResponse>() {
				@Override
				public void onFailure(Throwable caught) {
					logger.severe("Remote procedure call successful failed.");
					SC.say(caught.getClass().getName() + ": " + caught.getMessage());
					SC.clearPrompt();
				}

				@Override
				public void onSuccess(WMSGetCapabilitiesResponse result) {
					logger.info("Remote procedure call successful.");
					SC.clearPrompt();

					WMSSelector wmsSelector = new WMSSelector(eventBus, result.getWMSLayers(), selectedCoverage);
					wmsSelector.show();
					display.resetWindow();
					display.hideDialog();
				}
			};
			
			WMSGetCapabilitiesRequest request = new WMSGetCapabilitiesRequest(serviceURL, layerList);
			request.setExactMatches(false);

			logger.info("Making wmsGetCapabilities remote procedure call");
			wmsService.wmsGetCapabilities(request, callback);
		} else {
			String layer = createWMSForm.getValueAsString("LAYER_NAME");
			WCSGetCoverageAndStoreRequest request = new WCSGetCoverageAndStoreRequest(
					selectedCoverage);
			request.setLayerName(layer);

			// Set up the callback object.
			AsyncCallback<WCSGetCoverageAndStoreResponse> callback = new AsyncCallback<WCSGetCoverageAndStoreResponse>() {
				@Override
				public void onFailure(Throwable caught) {
					SC.clearPrompt();
					SC.say(caught.getMessage());
				}

				@Override
				public void onSuccess(
						WCSGetCoverageAndStoreResponse response) {
					logger.info("Remote procedure call successful");
					SC.clearPrompt();
					HashSet<Data> layers = new HashSet<Data>(1);
					layers.add(response.getWCSCoverage());
					eventBus.fireEvent(new AddLayersEvent(layers));
					display.hideDialog();
				}
			};

			logger.info("Making service call");
			wcsService.wcsGetCoverageAndStore(request, callback);
		}
	}

}
