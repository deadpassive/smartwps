package uk.ac.glam.smartwps.client.wps;

import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.shared.response.WPSExecuteResponse;
import uk.ac.glam.smartwps.shared.wps.output.ProcessOutput;
import uk.ac.glam.smartwps.shared.wps.output.WCSProcessOutput;
import uk.ac.glam.smartwps.shared.wps.output.WFSProcessOutput;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import java.util.List;

/**
 * This tab set is used to store the results of WPS processes in the SmartWPS environment.
 * 
 * @author jbritton
 *
 */
public class ResultsTabSet extends TabSet {
	
	/**
	 * TODO: document
	 */
	protected static final String IDENTIFIER = "identifier";
	/**
	 * TODO: document
	 */
	protected static final String TITLE = "title";
	/**
	 * TODO: document
	 */
	protected static final String MIME_TYPE = "mimeType";
	/**
	 * TODO: document
	 */
	protected static final String VALUE = "value";
	/**
	 * TODO: document
	 */
	protected static final String ACTION = "action";
	
	/**
	 * Creates a new ResultsTabSet.
	 */
	public ResultsTabSet() {
		super();
		
		Tab welcomeTab = new Tab("Info");
		HLayout welcomePane = new HLayout();
		welcomeTab.setPane(welcomePane);
		
		addTab(welcomeTab);
	}

	/**
	 * Adds a new tab to display data from the given WPSExecuteResponse.
	 * @param result
	 */
	public void addProcessResultsTab(WPSExecuteResponse result) {
		Tab newTab = new Tab(result.getProcessIdentifier());
		newTab.setCanClose(true);
				
		VLayout resultsDetails = new VLayout();
		Label titleLabel = new Label("<b>Creation time:</b> " + result.getCreationTime());
		resultsDetails.addMember(titleLabel);
		titleLabel.setHeight(25);
		
		ListGrid listGrid = new ListGrid() {

			@Override
			protected Canvas createRecordComponent(ListGridRecord record,
					Integer colNum) {
				String fieldName = this.getFieldName(colNum);  
		        if (fieldName.equals("action")) {
					final ProcessOutputRecord poRecord = (ProcessOutputRecord)record;
					final ProcessOutput processOutput = poRecord.getProcessOutput();
	            	if (processOutput instanceof WCSProcessOutput) {
	            		// Allow user to add WMS to map
	            		final IButton button = new IButton();  
				        button.setHeight(18);  
				        button.setTitle("Add To Map");  
				        button.addClickHandler(new ClickHandler() {
				            @Override
							public void onClick(ClickEvent event) {
				            	SmartWPS.getSmartWPS().getDataTree().addWCSCoverage(((WCSProcessOutput)processOutput).getWcsCoverage(), true);
				            }  
				        });  
				        return button;
	            	} else if (processOutput instanceof WFSProcessOutput) {
	            		// All user to add WFS to map
	            		final IButton button = new IButton();  
				        button.setHeight(18);  
				        button.setTitle("Add To Map");  
				        button.addClickHandler(new ClickHandler() {
				            @Override
							public void onClick(ClickEvent event) {
				            	SmartWPS.getSmartWPS().getDataTree().addWFSFeature(((WFSProcessOutput)processOutput).getFeatureType(), true);
				            }  
				        });  
				        return button;
	            	}
		        }
		        
		        return null;
			}
			
		};
		ListGridField idField = new ListGridField(IDENTIFIER, "ID");
		ListGridField titleField = new ListGridField(TITLE, "Title");
		ListGridField mimeTypeField = new ListGridField(MIME_TYPE, "MimeType");
		ListGridField valueField = new ListGridField(VALUE, "Value");
		ListGridField actionField = new ListGridField(ACTION, "Action");
		actionField.setWidth(100);
		listGrid.setHeight("*");
		
		listGrid.setShowRecordComponents(true);          
		listGrid.setShowRecordComponentsByCell(true);
		
		listGrid.setFields(idField, titleField, mimeTypeField, valueField, actionField);
		resultsDetails.addMember(listGrid);
		
		List<ProcessOutput> outputs = result.getProcessOutputs();
        for (ProcessOutput processOutput : outputs) {
			listGrid.addData(new ProcessOutputRecord(processOutput));
		}
		
		newTab.setPane(resultsDetails);
		
		addTab(newTab);
	}
}
