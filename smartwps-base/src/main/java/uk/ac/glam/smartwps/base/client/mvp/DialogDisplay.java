package uk.ac.glam.smartwps.base.client.mvp;

/**
 * TODO: document
 * 
 * @author Jon Britton
 * @param <PresenterType>
 */
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
	
	/**
	 * TODO: document
	 */
	public void hideDialog();
}
