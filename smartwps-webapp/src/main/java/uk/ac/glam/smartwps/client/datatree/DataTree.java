package uk.ac.glam.smartwps.client.datatree;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.gwtopenmaps.openlayers.client.control.WMSGetFeatureInfo;
import org.gwtopenmaps.openlayers.client.control.WMSGetFeatureInfoOptions;
import org.gwtopenmaps.openlayers.client.event.GetFeatureInfoListener;
import org.gwtopenmaps.openlayers.client.event.MapClickListener;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.WMS;

import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.shared.wcs111.WCSCoverage;
import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.util.ValueCallback;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.DropEvent;
import com.smartgwt.client.widgets.events.DropHandler;
import com.smartgwt.client.widgets.events.MouseMoveEvent;
import com.smartgwt.client.widgets.events.MouseMoveHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import java.util.List;
import uk.ac.glam.smartwps.client.map.OLMap;
import uk.ac.glam.smartwps.shared.util.StringUtils;

/**
 * A DataTree displays the current layers in a SmartWPS workspace.
 * @author Jon Britton
 *
 */
public class DataTree extends TreeGrid {

	private Tree tree;
	private boolean nodeDropped = false;
	//protected TreeNode selectedLeaf;
	private WMSGetFeatureInfo wmsSelectControl;
	private boolean wmsGetFeatureActivated;
	private static final Logger LOGGER = Logger.getLogger("smartwps.client");
	@SuppressWarnings("unused")
	private DataTreeContextHandler contextHandler;
	private final OLMap map;

	/**
	 * TODO: document
     * 
     * @param map 
     */
	public DataTree(OLMap map) {
		this.map = map;
		// TODO: Why is this here?? Should be moved to new OLMap class
		map.getOLMap().addMapClickListener(new MapClickListener() {
			@Override
			public void onClick(MapClickEvent mapClickEvent) {
				if ((wmsSelectControl != null) && (wmsGetFeatureActivated)) {
					SmartWPS.getSmartWPS().getInfoPane().setContents("Loading...");
				}
			}
		});

		TreeGridField checkboxField = new TreeGridField("selected", "X");
		checkboxField.setWidth(45);
		TreeGridField name = new TreeGridField("localname", "Name");

		setFields(checkboxField, name);

		tree = new Tree();
		tree.setModelType(TreeModelType.PARENT);
		tree.setNameProperty("localname");
		tree.setIdField("localname");
		tree.setShowRoot(false);

		setData(tree);
		setShowHeader(false);
		setLeaveScrollbarGap(false);
		setSelectionType(SelectionStyle.SINGLE);
		setAnimateFolders(true);
		setCanReorderRecords(true);
		setShowRecordComponents(true);
		setShowRecordComponentsByCell(true);

		// Need this workaround because onDrop in DropHandler is calld before
		// the Tree changes the layer order.
		addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (nodeDropped) {
					updateMapLayerOrder();
					nodeDropped = false;
				}
			}
		});

		addDropHandler(new DropHandler() {
			@Override
			public void onDrop(DropEvent event) {
				nodeDropped = true;
			}
		});

		createContextMenu();
	}

	/**
	 * Returns a list of all WMSLayers in the DataTree
	 * 
	 * @return the list of WMSLayers
	 */
	public ArrayList<WMSLayer> getWMSLayers() {
		TreeNode[] nodes = tree.getChildren(tree.getRoot());
		ArrayList<WMSLayer> dataList = new ArrayList<WMSLayer>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof WMSNode) {
				WMSNode node = (WMSNode) nodes[i];
				dataList.add(node.getWMSLayer());
			}
		}
		return dataList;
	}

	/**
	 * Returns a list of all WCSCoverages in the DataTree
	 * 
	 * @return the list of WCSCoverages
	 */
	public ArrayList<WCSCoverage> getWCSCoverages() {
		TreeNode[] nodes = tree.getChildren(tree.getRoot());
		ArrayList<WCSCoverage> dataList = new ArrayList<WCSCoverage>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof CoverageNode) {
				CoverageNode node = (CoverageNode) nodes[i];
				dataList.add(node.getCoverage());
			}
		}
		return dataList;
	}
	
	/**
	 * @return all of the CoverageNode objects currently in the tree
	 */
	public ArrayList<CoverageNode> getCoverageNodes() {
		TreeNode[] nodes = tree.getChildren(tree.getRoot());
		ArrayList<CoverageNode> nodeList = new ArrayList<CoverageNode>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof CoverageNode) {
				nodeList.add((CoverageNode) nodes[i]);
			}
		}
		return nodeList;
	}

	@Override
	protected Canvas createRecordComponent(final ListGridRecord record,
			Integer colNum) {
		String fieldName = this.getFieldName(colNum);
		if (fieldName.equals("selected")) {
			DynamicForm form = new DynamicForm();
			CheckboxItem cbi = new CheckboxItem();
			// cbi.setWidth(50);
			cbi.setShowLabel(false);
			cbi.setShouldSaveValue(false);
			cbi.setValue(true);
			cbi.addChangedHandler(new ChangedHandler() {
				@Override
				public void onChanged(ChangedEvent event) {
					((DataTreeNode) record).setIsVisible((Boolean) event.getValue());
				}
			});
			cbi.setAlign(Alignment.LEFT);
			form.setAlign(Alignment.LEFT);
			form.setItems(cbi);
			// Tweak the form so the checkbox displays properly...
			form.setWidth(15);
			form.setNumCols(2);
			form.setColWidths(0, "*");
			form.setCellPadding(0);
			return form;
		}

		return null;
	}

	private void createContextMenu() {
		LOGGER.info("Creating context menu");
		contextHandler = new DataTreeContextHandler(this);
	}
	
	/**
	 * TODO: document
	 * @param node
	 */
	public void removeLayer(DataTreeNode node) {
        map.removeLayer(node.getMapLayer());
		tree.remove(node);
		resetWMSSelectControl();
	}
	
	/**
	 * TODO: document
	 * @param dtn
	 */
	public void showRenameWindow(final DataTreeNode dtn) {
		Dialog dialogProperties = new Dialog();
		dialogProperties.setWidth(300);
		SC.askforValue("Rename Layer", "Enter new layer name", dtn.getLocalName(), new ValueCallback() {
			@Override
			public void execute(String value) {
				if (!StringUtils.isNullOrEmpty(value)) {
					dtn.setLocalName(value);
					refreshFields();
				}
			}
		}, dialogProperties);
	}

	private void updateMapLayerOrder() {
		TreeNode[] nodes = tree.getChildren(tree.getRoot());

		for (int i = 0; i < nodes.length; i++) {
			Layer layer = (((DataTreeNode) nodes[nodes.length - i - 1]).getMapLayer());
			map.setLayerIndex(layer, i);
		}
	}

	/**
	 * Retrieve the WMS layer object with the given id from the tree.
	 * @param layerId the id of the WMS layer
	 * @return the WMSLayer with the given id, or null if none exists
	 */
	public WMSLayer getWMSLayer(String layerId) {
		TreeNode[] nodes = tree.getChildren(tree.getRoot());

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof WMSNode) {
				WMSLayer layer = ((WMSNode) nodes[i]).getWMSLayer();
				if (layer.getName().equals(layerId)) {
                    return layer;
                }
			}
		}
		return null;
	}

	/**
	 * TODO: document
	 * @param node
	 */
	protected void zoomToLayer(DataTreeNode node) {
		map.zoomToExtent(node.getExtent());
	}

	/**
	 * TODO: document
	 * @param selection
	 */
	public void addWMSLayer(WMSLayer selection) {
		WMSNode wmsNode = new WMSNode(selection);
		addNewNode(wmsNode);
	}

	/**
	 * TODO: document
	 * @param wcsCoverage
	 */
	public void addWCSCoverage(WCSCoverage wcsCoverage) {
		addWCSCoverage(wcsCoverage, false);
	}
	
	/**
	 * TODO: document
	 * @param wcsCoverage
	 * @param rename
	 */
	public void addWCSCoverage(WCSCoverage wcsCoverage, boolean rename) {
		CoverageNode covNode = new CoverageNode(wcsCoverage);
		if (rename) {
            showRenameWindow(covNode);
        }
		addNewNode(covNode);
	}
	
	/**
	 * TODO: document
	 * @param wfsFeatureType
	 */
	public void addWFSFeature(WFSFeatureType wfsFeatureType) {
		addWFSFeature(wfsFeatureType, false);
	}
	
	/**
	 * TODO: document
	 * @param wfsFeatureType
	 * @param rename
	 */
	public void addWFSFeature(WFSFeatureType wfsFeatureType, boolean rename) {
		FeatureNode featNode = new FeatureNode(wfsFeatureType);
		if (rename) {
            showRenameWindow(featNode);
        }
		addNewNode(featNode);
	}
	
	private void addNewNode(DataTreeNode newNode) {
		// Check if another node exists with the same name
		TreeNode[] nodes = tree.getChildren(tree.getRoot());
		for (int i = 0; i < nodes.length; i++) {
			if (((DataTreeNode)nodes[i]).getLocalName().equalsIgnoreCase(newNode.getLocalName())) {
				newNode.setLocalName(newNode.getLocalName() + "(1)");
			}
		}
		tree.add(newNode, tree.getRoot(), 0);
		
		//updateMapLayerOrder();

		// Zoom to layer if it's the first layer added
		if (tree.getChildren(tree.getRoot()).length == 1) {
			zoomToLayer(newNode);
		}
		
		resetWMSSelectControl();
	}

	/**
	 * Returns the WCSCoverage with the given local name
	 * @param localName
	 * @return the WCSCoverage with the given local name
	 */
	public WCSCoverage getWCSCoverageByLocalName(String localName) {
		TreeNode[] nodes = tree.getChildren(tree.getRoot());

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof CoverageNode) {
				CoverageNode covNode = (CoverageNode) nodes[i];
				if (covNode.getLocalName().equals(localName)) {
					return covNode.getCoverage();
				}
			}
		}
		return null;
	}
	
	
	/**
	 * @return all WMS OpenLayers map layers, including those associated with WCS coverages.
	 */
	public List<WMS> getWMSMapLayers() {
		TreeNode[] nodes = tree.getChildren(tree.getRoot());
		ArrayList<WMS> mapList = new ArrayList<WMS>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof WMSNode) {
				WMSNode node = (WMSNode) nodes[i];
				mapList.add(node.getMapLayer());
			}
		}
		return mapList;
	}
	
	/**
	 * Updates the WMS GetFeatureInfo control to reflect recent changes in the DataTree.
	 */
	public void resetWMSSelectControl() {
		if (wmsSelectControl != null) {
            map.removeControl(wmsSelectControl);
        }
		WMSGetFeatureInfoOptions options = new WMSGetFeatureInfoOptions();
        List<WMS> wmsLayers = getWMSMapLayers();
		options.setLayers(wmsLayers.toArray(new WMS[wmsLayers.size()]));
		options.setDrillDown(true);
		options.setQueryVisible(true);
		options.setInfoFormat("application/vnd.ogc.gml");
		
		wmsSelectControl = new WMSGetFeatureInfo(options);
		wmsSelectControl.addGetFeatureListener(new GetFeatureInfoListener() {
			
			@Override
			public void onGetFeatureInfo(
					org.gwtopenmaps.openlayers.client.event.GetFeatureInfoListener.GetFeatureInfoEvent eventObject) {
				SmartWPS.getSmartWPS().getInfoPane().setContents(eventObject.getText());
				//VectorFeature[] features = eventObject.getFeatures();
				//System.out.println("FeatureInfo: " + eventObject.getText());
				//FramedCloud popup = new FramedCloud("MapFeaturePopup", features[0].getCenterLonLat(), new Size(300,400), eventObject.getText(), null, true);
			}
		});
		
		map.addControl(wmsSelectControl);
		LOGGER.info("Adding:");
        for (WMS layer : wmsLayers) {
            LOGGER.info(layer.getName());
        }
	}
	
	/**
	 * TODO: document
	 */
	public void activateWMSGetFeature() {
		wmsGetFeatureActivated = true;
		if (wmsSelectControl != null) {
            wmsSelectControl.activate();
        }
	}
	
	/**
	 * TODO: document
	 */
	public void deactivateWMSGetFeature() {
		wmsGetFeatureActivated = false;
		if (wmsSelectControl != null) {
            wmsSelectControl.deactivate();
        }
	}

	/**
	 * @return all WFSFeatureType objects currently in the list
	 */
	public ArrayList<WFSFeatureType> getWFSFeatureTypes() {
		TreeNode[] nodes = tree.getChildren(tree.getRoot());
		ArrayList<WFSFeatureType> dataList = new ArrayList<WFSFeatureType>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof FeatureNode) {
				FeatureNode node = (FeatureNode) nodes[i];
				dataList.add(node.getFeatureType());
			}
		}
		return dataList;
	}

	/**
	 * Retrieve the WFSFeatureType with the given name.
	 * @param typeName the name of the WFS feature type
	 * @return the WFSFeatureType with the given type name, or null if none exists
	 */
	public WFSFeatureType getWFSFeatureType(String typeName) {
		TreeNode[] nodes = tree.getChildren(tree.getRoot());

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof FeatureNode) {
				WFSFeatureType feature = ((FeatureNode) nodes[i]).getFeatureType();
				if (feature.getTypeName().equals(typeName)) {
					LOGGER.info("Feature found!");
					return feature;
				}
			}
		}
		return null;
	}
}
