package uk.ac.glam.smartwps.client.datatree;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.layer.Layer;

import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * Parent class for all map data node types.
 * 
 * @author Jon Britton
 */
public abstract class DataTreeNode extends TreeNode {
	/**
	 * The OpenLayers Layer associated with this node.
	 */
	protected Layer mapLayer;
	/**
	 * The right-click context menu for this node.
	 */
	protected Menu contextMenu;
	
	private boolean visible;
	
	/**
	 * @return the local workspace name for this node
	 */
	public String getLocalName() {
		return getAttribute("localname");
	}

	/**
	 * Set's the workspace name for this node. This is the name that will be displayed in the DataTree.
	 * @param localName
	 */
	public void setLocalName(String localName) {
		setAttribute("localname", localName);
	}

	/**
	 * @return the OpenLayers Layer associated with this node
	 */
	public Layer getMapLayer() {
		return mapLayer;
	}

	/**
	 * Toggle whether the node's layer is visible on the map.
	 */
	public void toggleIsVisible() {
		setIsVisible(!this.isVisible());
	}
	
	/**
	 * @return whether the node's layer is visible on the map
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Set whether this node's layer is visible on the map.
	 * @param visible whether to display the layer
	 */
	public void setIsVisible(boolean visible) {
		this.visible = visible;
		this.mapLayer.setIsVisible(visible);
	}
	
	/**
	 * Add the nodes layer to the workspace map.
	 */
	abstract void addLayerToMap();
	
	/**
	 * @return the extent of the layer wrapped by this node.
	 */
	public abstract Bounds getExtent();

	/**
	 * @return the right-click context menu for this node.
	 */
	public abstract Menu getContextMenu();
}
