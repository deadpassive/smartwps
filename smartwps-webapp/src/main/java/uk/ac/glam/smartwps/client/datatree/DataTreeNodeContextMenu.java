package uk.ac.glam.smartwps.client.datatree;

import uk.ac.glam.smartwps.client.SmartWPS;

import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class DataTreeNodeContextMenu extends Menu {

	/**
	 * TODO: document
	 */
	protected DataTree dataTree;
	private MenuItem opacityMenu;
	private DataTreeNode dtNode;

	/**
	 * TODO: document
	 * @param node
	 */
	public DataTreeNodeContextMenu(DataTreeNode node) {
		this.dtNode = node;
		this.dataTree = SmartWPS.getSmartWPS().getDataTree();
		
		setShowShadow(true);
		setShadowDepth(10);
				
		MenuItem zoomToLayer = new MenuItem("Zoom to Layer");
		zoomToLayer.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				dataTree.zoomToLayer(dtNode);
			}
		});
		
		MenuItem delete = new MenuItem("Delete");
		delete.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				// Remove from DataTree
				dataTree.removeLayer(dtNode);
			}
		});
		
		MenuItem rename = new MenuItem("Rename");
		rename.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				dataTree.showRenameWindow(dtNode);
			}
		});
				
		// Opacity
		opacityMenu = new MenuItem("Opacity");
		Menu opacitySubmenu = new Menu();

		for (int i = 1; i <= 10; i++) {
			opacitySubmenu.addItem(createOpacityMenuItem(i*0.1f));
		}
		
		opacityMenu.setSubmenu(opacitySubmenu);
		
		setItems(zoomToLayer, delete, rename, opacityMenu);
	}
	
	private MenuItem createOpacityMenuItem(final float opacity) {
		MenuItem mi = new MenuItem(Math.round(opacity*100) + "%");
		mi.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				dtNode.getMapLayer().setOpacity(opacity);
				
//				MenuItem [] items = event.getMenu().getItems();
//				for (int i = 0; i < items.length; i++) {
//					items[i].setChecked(false);
//				}
				event.getItem().setChecked(true);
			}
		});
		return mi;
	}

	
}
