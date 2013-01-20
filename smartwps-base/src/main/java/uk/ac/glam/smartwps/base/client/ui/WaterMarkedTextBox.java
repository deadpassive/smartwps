package uk.ac.glam.smartwps.base.client.ui;

import com.google.gwt.user.client.ui.TextBox;

/**
 * Extension of the GWT text box to allow watermarking.
 * 
 * @author Jon Britton
 */
public class WaterMarkedTextBox extends TextBox {
	
	/**
	 * TODO: document
	 * @param placeholder
	 */
	public void setPlaceholder(String placeholder) {
		getElement().setAttribute("placeholder", placeholder);
	}
}
