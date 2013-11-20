package uk.ac.glam.smartwps.base.client.mvp;

import uk.ac.glam.smartwps.base.client.ui.CloseCaption;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DialogBox;

public class DialogViewWidget<PresenterType> extends DialogBox implements DialogDisplay<PresenterType> {

	protected PresenterType presenter;

	public DialogViewWidget(String title) {
		super(new CloseCaption());
		CloseCaption caption = (CloseCaption) getCaption();
		caption.doOnClose(new Command() {
			
			@Override
			public void execute() {
				hide();
			}
		});
		
		setText(title);
		
		// Fix for running alongside SmartGWT stuff
		getElement().getStyle().setZIndex(1000000);
	}
	
	@Override
	public void showDialog() {
		center();
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
