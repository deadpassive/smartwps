package uk.ac.glam.smartwps.client.addwmsdialog;

import uk.ac.glam.smartwps.shared.Data;

/**
 * TODO: document
 * 
 * @author Jon Britton
 * @param <LayerType>
 * @deprecated 
 */
public interface AddLayerCallback<LayerType extends Data> {

	/**
	 * TODO: document
	 * @param layer
	 */
	public void addLayer(LayerType layer);
}
