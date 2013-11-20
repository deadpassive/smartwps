package uk.ac.glam.smartwps.base.client.sgwt;

import com.smartgwt.client.widgets.Window;

import uk.ac.glam.smartwps.base.client.mvp.DialogDisplay;

/**
 * Base class for SmartGWT dialog views.
 * 
 * @author Jon Britton
 * @param <PresenterType>
 */
public class SGWTDialogViewWidget<PresenterType> extends Window implements DialogDisplay<PresenterType> {

	protected PresenterType presenter;
	
	/**
	 * TODO: document
	 * @param title
	 */
	public SGWTDialogViewWidget(String title, int width, int height) {
		super();
		
		setTitle(title);
		setAutoCenter(true);
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public void showDialog() {
		show();
	}

	@Override
	public void setPresenter(PresenterType presenter) {
		this.presenter = presenter;
	}

	@Override
	public void hideDialog() {
		hide();
	}

}
