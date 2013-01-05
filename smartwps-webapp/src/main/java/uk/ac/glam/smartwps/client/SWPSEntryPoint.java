package uk.ac.glam.smartwps.client;

import uk.ac.glam.smartwps.client.addwmsdialog.AddWMSDialog;
import uk.ac.glam.smartwps.client.addwmsdialog.AddWMSDialogGwt;
import uk.ac.glam.smartwps.client.addwmsdialog.AddWMSPresenter;
import uk.ac.glam.smartwps.client.addwmsdialog.AddWMSPresenterImpl;
import uk.ac.glam.smartwps.client.loggerdialog.LoggerPresenter;
import uk.ac.glam.smartwps.client.loggerdialog.LoggerPresenterImpl;
import uk.ac.glam.smartwps.client.loggerdialog.LoggerDialog;
import uk.ac.glam.smartwps.client.mvp.AppPlaceHistoryMapper;
import uk.ac.glam.smartwps.client.net.WMSRequestService;
import uk.ac.glam.smartwps.client.net.WMSRequestServiceAsync;
import uk.ac.glam.smartwps.client.net.WPSRequestService;
import uk.ac.glam.smartwps.client.net.WPSRequestServiceAsync;
import uk.ac.glam.smartwps.client.place.SmartWPSPlace;
import uk.ac.glam.smartwps.client.processresults.ProcessResultsActivityMapper;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

/**
 * TODO: document.
 * 
 * @author Jon Britton
 */
public class SWPSEntryPoint implements EntryPoint {

    private Place defaultPlace = new SmartWPSPlace();

    @SuppressWarnings("unused")
	@Override
    public void onModuleLoad() {
    	WPSRequestServiceAsync wpsService = GWT.create(WPSRequestService.class);
    	WMSRequestServiceAsync wmsService = GWT.create(WMSRequestService.class);
    	ClientFactory clientFactory = GWT.create(ClientFactory.class);
    	EventBus eventBus = clientFactory.getEventBus();
    	AppLayout layout = new AppLayoutImpl(eventBus, wpsService);
        PlaceController placeController = clientFactory.getPlaceController();
        
        AddWMSPresenter.Display wmsDialog = new AddWMSDialog();
//        AddWMSPresenter.Display wmsDialog = new AddWMSDialogGwt();
		AddWMSPresenter wmsPresenter = new AddWMSPresenterImpl(eventBus, wmsDialog, wmsService);
        
        LoggerDialog loggerDialog = new LoggerDialog();
        LoggerPresenter loggerPresenter = new LoggerPresenterImpl(eventBus, loggerDialog);
        
        // TODO: this is a temporary measure while I transition to MVP
        SmartWPS.setEventBus(eventBus);
        
		ActivityMapper processResultsActivityMapper = new ProcessResultsActivityMapper(clientFactory);
		ActivityManager processResultsActivityManager = new ActivityManager(processResultsActivityMapper, eventBus);
		processResultsActivityManager.setDisplay(layout.getResultsHolder());

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

//		RootPanel.get().add(layout);
		// Goes to place represented on URL or default place
		historyHandler.handleCurrentHistory();
    }
}
