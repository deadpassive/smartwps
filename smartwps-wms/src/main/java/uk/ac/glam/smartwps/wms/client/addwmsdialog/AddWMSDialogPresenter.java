package uk.ac.glam.smartwps.wms.client.addwmsdialog;

import java.util.List;
import java.util.Set;

import uk.ac.glam.smartwps.base.client.mvp.DialogView;
import uk.ac.glam.smartwps.wms.shared.WMSLayer;

/**
 * @TODO: document
 * @author jonb
 *
 */
public interface AddWMSDialogPresenter {
	
	/**
	 * @TODO: document
	 * @author jonb
	 *
	 */
	public interface Display extends DialogView<AddWMSDialogPresenter> {
		
		/**
		 * TODO: document
		 * @param wmsLayers
		 */
		public void setWMSLayers(List<WMSLayer> wmsLayers);

		/**
		 * Tell the view to handle a failure.
		 * @param message the failure message
		 */
		public void doFailure(String message);
	}
	
	/**
	 * TODO: document
	 * @param url
	 */
	public void retrieveWMSLayer(String url);

	/**
	 * TODO: document
	 * @param wmsLayers
	 */
	public void addLayers(Set<WMSLayer> wmsLayers);
}
