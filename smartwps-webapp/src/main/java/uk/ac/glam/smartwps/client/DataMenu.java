package uk.ac.glam.smartwps.client;

import uk.ac.glam.smartwps.client.addwmsdialog.AddMapWindow;
import uk.ac.glam.smartwps.client.wcs.AddCoverageWindow;
import uk.ac.glam.smartwps.client.wfs.AddWFSWindow;
import uk.ac.glam.smartwps.client.wps.AddProcessWindow;

import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * TODO: document
 * @author Jon Britton
 *
 */
public class DataMenu extends Menu {
	
	private AddMapWindow wmsWindow;
	private AddCoverageWindow wcsWindow;
	private AddWFSWindow wfsWindow;
	private AddProcessWindow wpsWindow;

	/**
	 * Create a new DataMenu.
	 */
	public DataMenu () {
		setShowShadow(true);
		setShadowDepth(3);
		
		MenuItem wpsItem = new MenuItem("Web Processing Service");
		wpsItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				if (wpsWindow == null) {
                    wpsWindow = new AddProcessWindow();
                }
				wpsWindow.show();
			}
		});
		
		MenuItem wmsItem = new MenuItem("Web Map Service", "wmsicon.png");
		wmsItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				if (wmsWindow == null) {
                    wmsWindow = new AddMapWindow();
                }
				wmsWindow.show();
			}
		});
		
		MenuItem wcsItem = new MenuItem("Web Coverage Service", "wcsicon.png");
		wcsItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				if (wcsWindow == null) {
                    wcsWindow = new AddCoverageWindow();
                }
				wcsWindow.show();
			}
		});
		
		MenuItem wfsItem = new MenuItem("Web Feature Service", "wfsicon.png");
		wfsItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				if (wfsWindow == null) {
                    wfsWindow = new AddWFSWindow();
                }
				wfsWindow.show();
			}
		});
		
		setItems(wmsItem, wcsItem, wfsItem, wpsItem);
	}
}