package uk.ac.glam.smartwps.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.gwtopenmaps.openlayers.client.Bounds;

import uk.ac.glam.smartwps.data.shared.ows.BoundsSerializable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * General client-side utility methods.
 * 
 * TODO: should probably move this somewhere else
 * 
 * @author Jon Britton
 */
public class ClientUtils {
    
    private ClientUtils() {}

	private static final Logger LOGGER = Logger.getLogger("smartwps.client");
	
	/**
	 * Tell the server the module URL.
	 * TODO: why are we doing this?
	 */
	public static void setModuleURL() {
		LOGGER.info("Setting module url");

		SmartWPS.getOWSRequestService().setModuleBaseURL(GWT.getModuleBaseURL(), new AsyncCallback<Object>() {
			@Override
			public void onFailure(Throwable caught) {
				LOGGER.log(Level.SEVERE, "Error setting module URL", caught);
			}

			@Override
			public void onSuccess(Object result) {
				LOGGER.info("Successfully set module URL");
			}
		});
	}

	/**
	 * Create a SmartWPS serializable bounds object from an OpenLayer Bounds.
	 * @param bounds the OpenLayers Bounds object
	 * @return a new serializable bounds
	 */
	public static BoundsSerializable createBoundsSerializable(Bounds bounds) {
		return new BoundsSerializable(bounds.getLowerLeftX(), bounds
				.getLowerLeftY(), bounds.getUpperRightX(), bounds
				.getUpperRightY(), null);
	}

	/**
	 * Create an OpenLayers Bounds object from a SmartWPS serializable bounds.
	 * @param bounds the serializable bounds object to convert
	 * @return a new OpenLayers Bounds object
	 */
	public static Bounds createBounds(BoundsSerializable bounds) {
		return new Bounds(bounds.getMinX(), bounds
				.getMinY(), bounds.getMaxX(), bounds
				.getMaxY());
	}
	
}
