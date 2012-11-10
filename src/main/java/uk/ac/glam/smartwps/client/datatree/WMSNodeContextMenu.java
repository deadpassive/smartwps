package uk.ac.glam.smartwps.client.datatree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WMSNodeContextMenu extends DataTreeNodeContextMenu {

	private MenuItem styleMenu;

	/**
	 * TODO: document
	 * @param node
	 */
	public WMSNodeContextMenu(final WMSNode node) {
		super(node);
		
		styleMenu = new MenuItem("Change Style");
		
		Menu submenu = new Menu();
		List<String> styles = node.getWMSLayer().getStyles();
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		for (Iterator<String> iterator = styles.iterator(); iterator.hasNext();) {
			final String style = iterator.next();
			final MenuItem menuItem = new MenuItem(style);
			menuItem.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(MenuItemClickEvent event) {
					String[] toArray = {style};
					node.setStyles(toArray);
				}
			});
			menuItems.add(menuItem);
		}
		submenu.setItems(menuItems.toArray(new MenuItem[0]));
		styleMenu.setSubmenu(submenu);
		
		addItem(styleMenu);
	}

}
