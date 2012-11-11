package uk.ac.glam.smartwps.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.OpenLayers;
import org.gwtopenmaps.openlayers.client.control.MousePosition;
import org.gwtopenmaps.openlayers.client.control.Navigation;
import org.gwtopenmaps.openlayers.client.control.PanZoomBar;
import org.gwtopenmaps.openlayers.client.util.JObjectArray;
import org.gwtopenmaps.openlayers.client.util.JSObject;

import uk.ac.glam.smartwps.client.datatree.DataTree;
import uk.ac.glam.smartwps.client.logging.LoggerWindow;
import uk.ac.glam.smartwps.client.net.OWSRequestService;
import uk.ac.glam.smartwps.client.net.OWSRequestServiceAsync;
import uk.ac.glam.smartwps.client.wps.ResultsTabSet;
import uk.ac.glam.smartwps.client.wps.RunProcessListGrid;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SmartWPS implements EntryPoint {

	private static SmartWPS smartWPS;
	
	private static OWSRequestServiceAsync owsService = GWT.create(OWSRequestService.class);
	
	/**
	 * Client side logger
	 */
	public static Logger LOGGER = Logger.getLogger("smartwps.client");
	
	private MapWidget mapWidget;
	private LoggerWindow loggerWindow;
	private DataTree dataTree;
	private HTMLPane infoPane;
	private RunProcessListGrid runProcessList;
	private ResultsTabSet resultsTabSet;
	
	/**
	 * @return the data menu widget
	 */
	public DataMenu getDataMenu() {
		return dataMenu;
	}

	private ToolStripMenuButton createAddMenu() {
		dataMenu = new DataMenu();
		
		return new ToolStripMenuButton("Add", dataMenu);
	}
	
	private static MapWidget createMap() {
		LOGGER.info("Creating Map");
		final MapWidget mw = createMap("EPSG:4326");
		mw.getMap().setMaxExtent(new Bounds(-180, -90, 180, 90));
		mw.setWidth("100%");
		mw.setHeight("100%");
		return mw;
	}
	
	private static MapWidget createMap(String projection) {
		MapOptions defaultMapOptions = new MapOptions();
		//In OL, the map gets PanZoom, Navigation, ArgParser, and Attribution Controls
		//by default. Do setControls below to start with a map without Controls.
		defaultMapOptions.setControls(new JObjectArray(new JSObject[] {}));
		defaultMapOptions.setAllOverlays(true);
		//defaultMapOptions.setProjection("EPSG:900913");
		defaultMapOptions.setProjection(projection);
		//defaultMapOptions.setDisplayProjection(new Projection("EPSG:4326"));
		//defaultMapOptions.setMaxExtent(new Bounds(-20037508.34,-20037508.34,20037508.34,20037508.34));
		defaultMapOptions.setNumZoomLevels(19);
		defaultMapOptions.setUnits("m");

		MapWidget mw = new MapWidget("100%", "100%", defaultMapOptions);
		
		//Adding controls to the Map
		mw.getMap().addControl(new PanZoomBar());
		mw.getMap().addControl(new Navigation());
		mw.getMap().addControl(new MousePosition());
		//mw.getMap().addControl(new LayerSwitcher());
				
		return mw;
	}
	
	private ToolStrip createToolStrip() {
		ToolStrip toolStrip = new ToolStrip();
		toolStrip.setWidth100();
		
		toolStrip.addMenuButton(createAddMenu());
		toolStrip.addSeparator();
		
		final ToolStripButton mapQueryButton = new ToolStripButton("?");
		mapQueryButton.setActionType(SelectionType.CHECKBOX);
		toolStrip.addButton(mapQueryButton);
		mapQueryButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (mapQueryButton.getSelected())
					dataTree.activateWMSGetFeature();
				else
					dataTree.deactivateWMSGetFeature();
			}
		});
		
		final ToolStripButton showLoggerButton = new ToolStripButton("Log");
		toolStrip.addButton(showLoggerButton);
		showLoggerButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loggerWindow.show();
			}
		});
		
		return toolStrip;
	}
	
	private DataMenu dataMenu;

	/**
	 * @return the data tree widget
	 */
	public DataTree getDataTree() {
		return dataTree;
	}
	
	/**
	 * @return the info pane widget
	 */
	public HTMLPane getInfoPane() {
		return infoPane;
	}
	
	/**
	 * @return the OpenLayers Map object (not the widget)
	 */
	public Map getMap() {
		return mapWidget.getMap();
	}

	/**
	 * @return the results tab set widget
	 */
	public ResultsTabSet getResultsTabSet() {
		return resultsTabSet;
	}
	
	/**
	 * @return the process list widget
	 */
	public RunProcessListGrid getRunProcessList() {
		return runProcessList;
	}
	
	@Override
	public void onModuleLoad() {
		// Create global SmartWPS instance
		setSmartWPS(this);
		
		// Set up logger
		LOGGER.setLevel(Level.ALL);
		loggerWindow = new LoggerWindow();
		LOGGER.info("Loading SmartWPS");
		
		//set a proxyHost
		OpenLayers.setProxyHost(GWT.getModuleBaseURL() + "gwtOpenLayersProxy?targetURL=");
		ClientUtils.setModuleURL();
		LOGGER.info(OpenLayers.getProxyHost());
		
		VLayout mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();
		
		// Menu Bar
		mainLayout.addMember(createToolStrip());
		
		HLayout workspaceLayout = new HLayout();
		workspaceLayout.setWidth100();
		workspaceLayout.setHeight100();
		
		// the map
		mapWidget = createMap();
		// mapwidget doesn't display if set to 100%.  Hopefully this can be fixed...
		mapWidget.setHeight("600px");
		
		final SectionStack leftStack = new SectionStack();
		leftStack.setWidth(280);
		leftStack.setHeight100();
		leftStack.setShowResizeBar(true);
		leftStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		leftStack.setAnimateSections(true);
		
		// DATA SECTION
		SectionStackSection dataListSection = new SectionStackSection("Data");
		dataListSection.setExpanded(true);
		dataTree = new DataTree();
		dataListSection.setItems(dataTree);
		
		// PROCESSES SECTION
		SectionStackSection processSection = new SectionStackSection("Processes");
		runProcessList = new RunProcessListGrid();
		processSection.setItems(runProcessList);
		
		// INFO SECTION
		SectionStackSection instructionsSection = new SectionStackSection("Info");
		infoPane = new HTMLPane();
        instructionsSection.setItems(infoPane);  
        instructionsSection.setExpanded(false);  
		
        leftStack.setSections(dataListSection, processSection, instructionsSection);
        
        workspaceLayout.addMember(leftStack);
		
		final SectionStack rightStack = new SectionStack();
		rightStack.setWidth100();
		rightStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		rightStack.setAnimateSections(true);		
		
		final HLayout mapPanel = new HLayout();
		mapPanel.setID("MyMapPanel");
		mapPanel.setWidth100();  
		mapPanel.setHeight100();
		mapPanel.addMember(mapWidget);

		final SectionStackSection mapSection = new SectionStackSection("Map");
		mapSection.setItems(mapPanel);
		
		SectionStackSection resultSection = new SectionStackSection("Results");
		resultsTabSet = new ResultsTabSet();
		resultsTabSet.setWidth100();
		resultsTabSet.setHeight(350);
		resultSection.setItems(resultsTabSet);
		
		rightStack.setSections(mapSection, resultSection);
		
		workspaceLayout.addMember(rightStack);
		
		mainLayout.addMember(workspaceLayout);
		
		RootLayoutPanel.get().add(mainLayout);
		
		testSomething();
		
//		VectorOptions vectorOptions = new VectorOptions();
//		final Vector mapLayer = new Vector("roads", vectorOptions);
//
//		WFSProtocolOptions wfsProtocolOptions = new WFSProtocolOptions(
//				"http://li199-25.members.linode.com:8080/geoserver/ows",
//				"http://www.openplans.org/spearfish",
//				"roads");
//		wfsProtocolOptions.setSrsName("EPSG:4326");
//		wfsProtocolOptions.setVersion("1.0.0");
//
//		WFSProtocol wfsProtocol = new WFSProtocol(wfsProtocolOptions);
//		Callback callback = new Callback() {
//			public void computeResponse(Response response) {
//				try {
//					VectorFeature[] features = response.getFeatures();
//					mapLayer.addFeatures(features);
//
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		};
//		WFSProtocolCRUDOptions options = new WFSProtocolCRUDOptions(callback);
//		wfsProtocol.read(options);
//		
//		getMap().addLayer(mapLayer);
//		getMap().zoomToExtent(new Bounds(-103.88, 44.305, -103.62, 44.566));
	}

	private void testSomething() {
		//new ErrorWindow().show();
	}

	/**
	 * @return the RPC request services for OGC web services
	 */
	public static OWSRequestServiceAsync getOWSRequestService() {
		if (owsService == null)
			owsService = GWT.create(OWSRequestService.class);
		return owsService;
	}
	
	/**
	 * @return the global SmartWPS instance
	 */
	public static SmartWPS getSmartWPS() {
		return smartWPS;
	}
	
	private static void setSmartWPS(SmartWPS smartWPS) {
		SmartWPS.smartWPS = smartWPS;
	}
}
