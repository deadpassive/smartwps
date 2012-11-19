package uk.ac.glam.smartwps.client.datatree;

import com.smartgwt.client.widgets.tree.events.LeafContextClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafContextClickHandler;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class DataTreeContextHandler implements LeafContextClickHandler {

	private DataTree owner;

	/**
	 * TODO: document
	 * @param owner
	 */
    public DataTreeContextHandler(DataTree owner) {
		this.owner = owner;
		owner.addLeafContextClickHandler(this);
	}

	@Override
	public void onLeafContextClick(LeafContextClickEvent event) {
		final DataTreeNode selectedLeaf = (DataTreeNode) event.getLeaf();
		
		owner.setContextMenu(selectedLeaf.getContextMenu());
		
//		if (selectedLeaf instanceof WMSNode) {
//			Menu submenu = new Menu();
//			List<String> styles = ((WMSNode)selectedLeaf).getWMSLayer().getStyles();
//			ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
//            for (String style : styles) {
//				final MenuItem menuItem = new MenuItem(style);
//				menuItem.addClickHandler(new ClickHandler() {
//					public void onClick(MenuItemClickEvent event) {
//						String[] styles = {style};
//						((WMSNode) selectedLeaf).setStyles(styles);
//					}
//				});
//				menuItems.add(menuItem);
//			}
//			submenu.setItems(menuItems.toArray(new MenuItem[0]));
//			style.setSubmenu(submenu);
//		} else {
//			LOGGER.info("NULL");
//			owner.setContextMenu(null);
//		}
	}

}
