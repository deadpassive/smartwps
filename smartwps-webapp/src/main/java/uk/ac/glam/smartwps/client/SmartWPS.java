package uk.ac.glam.smartwps.client;

import java.util.logging.Logger;

import uk.ac.glam.smartwps.client.net.OWSRequestService;
import uk.ac.glam.smartwps.client.net.OWSRequestServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * TODO: remove responsibilities from entry point class - MVP!?
 */
public class SmartWPS {

	private static AppLayout appLayout;
	
	private static OWSRequestServiceAsync owsService = GWT.create(OWSRequestService.class);

	private static EventBus EVENT_BUS;
	
	/**
	 * Client side logger
	 */
	public static final Logger LOGGER = Logger.getLogger("smartwps.client");

	/**
	 * @return the RPC request services for OGC web services
	 */
	public static OWSRequestServiceAsync getOWSRequestService() {
		if (owsService == null) {
            owsService = GWT.create(OWSRequestService.class);
        }
		return owsService;
	}
	
	/**
	 * @return the global SmartWPS instance
	 */
	public static AppLayout getSmartWPS() {
		return appLayout;
	}
	
	/**
	 * TODO: remove this - decouple and use events instead
	 * TODO: document
	 * @param smartWPSView
	 */
	public static void setAppLayout(AppLayout smartWPSView) {
		SmartWPS.appLayout = smartWPSView;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public static EventBus getEventBus() {
		return EVENT_BUS;
	}

	/**
	 * TODO: document
	 * @param eventBus
	 */
	public static void setEventBus(EventBus eventBus) {
		EVENT_BUS = eventBus;
	}
}
