package uk.ac.glam.smartwps.client.addwmsdialog;

import java.util.List;

import uk.ac.glam.smartwps.client.DataSourceManager;
import uk.ac.glam.smartwps.client.event.AddLayerEvent;
import uk.ac.glam.smartwps.client.event.ShowWMSDialogEvent;
import uk.ac.glam.smartwps.client.event.ShowWMSDialogHandler;
import uk.ac.glam.smartwps.client.net.WMSRequestServiceAsync;
import uk.ac.glam.smartwps.shared.DataSource;
import uk.ac.glam.smartwps.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.shared.response.WMSGetCapabilitiesResponse;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class AddWMSPresenterImpl implements AddWMSPresenter {
	
	private Display view;
	private WMSRequestServiceAsync wmsService;
	private EventBus eventBus;

	/**
	 * TODO: document
	 * @param eventBus
	 * @param display
	 * @param wmsService 
	 */
	public AddWMSPresenterImpl(EventBus eventBus, final AddWMSPresenter.Display display, WMSRequestServiceAsync wmsService) {
		this.view = display;
		this.wmsService = wmsService;
		this.eventBus = eventBus;
		display.setPresenter(this);
		
		eventBus.addHandler(ShowWMSDialogEvent.TYPE, new ShowWMSDialogHandler() {
			
			@Override
			public void onShowDialog(ShowWMSDialogEvent event) {
				view.showDialog();
			}
		});
	}
	
	@Override
	public void retrieveWMSLayer(String url) {
		// Set up the callback object.
		AsyncCallback<WMSGetCapabilitiesResponse> callback = new AsyncCallback<WMSGetCapabilitiesResponse>() {
			@Override
			public void onFailure(Throwable caught) {
				view.doFailure(caught.getMessage());
			}

			@Override
			public void onSuccess(WMSGetCapabilitiesResponse result) {
				List<WMSLayer> wmsLayers = result.getWMSLayers();
				
				// Register DataSource (all layers should have the same DataSource)
				DataSource ds = wmsLayers.get(0).getDataSource();
				DataSourceManager.registerDataSource(ds.getUrl(), ds);
				
				view.setWMSLayers(wmsLayers);
			}
		};

		// Make the call to the stock price service.
		GWT.log("Making service call");
		WMSGetCapabilitiesRequest request = new WMSGetCapabilitiesRequest(url);
		wmsService.wmsGetCapabilities(request, callback);
	}
	
	@Override
	public void addLayer(WMSLayer wmsLayer) {
		eventBus.fireEvent(new AddLayerEvent((wmsLayer)));
	}
}

