package uk.ac.glam.smartwps.wms.client.addwmsdialog;

import java.util.List;
import java.util.Set;

import uk.ac.glam.smartwps.base.client.DataSourceManager;
import uk.ac.glam.smartwps.base.client.event.AddLayersEvent;
import uk.ac.glam.smartwps.base.shared.DataSource;
import uk.ac.glam.smartwps.wms.client.event.ShowWMSDialogEvent;
import uk.ac.glam.smartwps.wms.client.event.ShowWMSDialogHandler;
import uk.ac.glam.smartwps.wms.client.net.WMSRequestServiceAsync;
import uk.ac.glam.smartwps.wms.shared.WMSLayer;
import uk.ac.glam.smartwps.wms.shared.request.WMSGetCapabilitiesRequest;
import uk.ac.glam.smartwps.wms.shared.response.WMSGetCapabilitiesResponse;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class AddWMSDialogPresenterImpl implements AddWMSDialogPresenter {
	
	private Display view;
	private WMSRequestServiceAsync wmsService;
	private EventBus eventBus;

	/**
	 * TODO: document
	 * @param eventBus
	 * @param display
	 * @param wmsService 
	 */
	public AddWMSDialogPresenterImpl(EventBus eventBus, final AddWMSDialogPresenter.Display display, WMSRequestServiceAsync wmsService) {
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
	public void addLayers(Set<WMSLayer> wmsLayers) {
		eventBus.fireEvent(new AddLayersEvent((wmsLayers)));
	}
}

