package uk.ac.glam.smartwps.base.client.mvp;

/**
 * TODO: document
 * 
 * @author Jon Britton
 * @param <PresenterType>
 */
public interface DialogView<PresenterType> extends View {
			
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
