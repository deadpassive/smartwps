package uk.ac.glam.smartwps.base.client.mvp;

public interface DialogDisplay<PresenterType> {
	/**
	 * TODO: document
	 */
	public void showDialog();
			
	/**
	 * TODO: document
	 * @param presenter
	 */
	public void setPresenter(PresenterType presenter);
}
