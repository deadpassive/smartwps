package uk.ac.glam.smartwps.client.widget;

import com.google.gwt.user.client.ui.TextBox;

/**
 * Extension of the GWT text box to allow watermarking.
 * 
 * @author Jon Britton
 */
public class WaterMarkedTextBox extends TextBox {
//	private String placeholder;
	
	/**
	 * TODO: document
	 * @param placeholder
	 */
	public void setPlaceholder(String placeholder) {
//		this.placeholder = placeholder;
//		getElement().getStyle().setProperty("placeholder", placeholder);
		getElement().setAttribute("placeholder", placeholder);
	}
}
