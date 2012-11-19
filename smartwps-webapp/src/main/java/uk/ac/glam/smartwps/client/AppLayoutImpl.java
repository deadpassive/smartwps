package uk.ac.glam.smartwps.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasOneWidget;
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
import org.gwtopenmaps.openlayers.client.OpenLayers;
import uk.ac.glam.smartwps.client.datatree.DataTree;
import uk.ac.glam.smartwps.client.layout.SmartGWTSimplePanel;
import uk.ac.glam.smartwps.client.logging.LoggerWindow;
import uk.ac.glam.smartwps.client.map.OLMap;
import uk.ac.glam.smartwps.client.wps.RunProcessListGrid;

public class AppLayoutImpl extends Composite implements AppLayout {

    private final OLMap map;
    private final DataTree dataTree;
    private final RunProcessListGrid runProcessList;
    private final HTMLPane infoPane;
    private DataMenu dataMenu;
    private LoggerWindow loggerWindow;
	private SmartGWTSimplePanel resultsHolder;

	public AppLayoutImpl() {
        
        SmartWPS.setAppLayout(this);
        //set a proxyHost
		OpenLayers.setProxyHost(GWT.getModuleBaseURL() + "gwtOpenLayersProxy?targetURL=");
		ClientUtils.setModuleURL();
//		LOGGER.info(OpenLayers.getProxyHost());
		
		VLayout mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();
		
		// Menu Bar
		mainLayout.addMember(createToolStrip());
		
		HLayout workspaceLayout = new HLayout();
		workspaceLayout.setWidth100();
		workspaceLayout.setHeight100();
		
		// the map
		map = new OLMap();
		// mapwidget doesn't display if set to 100%.  Hopefully this can be fixed...
		map.setHeight("600px");
		
		final SectionStack leftStack = new SectionStack();
		leftStack.setWidth(280);
		leftStack.setHeight100();
		leftStack.setShowResizeBar(true);
		leftStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		leftStack.setAnimateSections(true);
		
		// DATA SECTION
		SectionStackSection dataListSection = new SectionStackSection("Data");
		dataListSection.setExpanded(true);
		dataTree = new DataTree(map);
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
		mapPanel.addMember(map);

		final SectionStackSection mapSection = new SectionStackSection("Map");
		mapSection.setItems(mapPanel);
		
		SectionStackSection resultSection = new SectionStackSection("Results");
		resultsHolder = new SmartGWTSimplePanel();
		resultsHolder.setHeight(350);
		resultSection.setItems(resultsHolder);
		
		mapSection.setExpanded(true);
		
		resultSection.setExpanded(true);
		
		rightStack.setSections(mapSection, resultSection);
		
		workspaceLayout.addMember(rightStack);
		
		mainLayout.addMember(workspaceLayout);
		initWidget(mainLayout);
	}
    
	private ToolStripMenuButton createAddMenu() {
		dataMenu = new DataMenu();
		
		return new ToolStripMenuButton("Add", dataMenu);
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
				if (mapQueryButton.getSelected()) {
                    dataTree.activateWMSGetFeature();
                } else {
                    dataTree.deactivateWMSGetFeature();
                }
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
    
    
    
    	
	/**
	 * @return the data menu widget
	 */
	public DataMenu getDataMenu() {
		return dataMenu;
	}
	
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
    @Override
	public OLMap getMap() {
		return map;
	}
	
	/**
	 * @return the process list widget
	 */
	public RunProcessListGrid getRunProcessList() {
		return runProcessList;
	}

	@Override
	public HasOneWidget getResultsHolder() {
		return resultsHolder;
	}
}
