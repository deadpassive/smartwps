package uk.ac.glam.smartwps.client.wps;


import uk.ac.glam.smartwps.shared.wps.DetailedProcessDescriptor;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * A list of WPS processes. Includes a "Run" button so that the process can be executed.
 * 
 * @author jbritton
 *
 */
public class RunProcessListGrid extends ListGrid {
	
	/**
	 * TODO: document
	 */
	public RunProcessListGrid() {
		ListGridField idField = new ListGridField("id","ID");
		//ListGridField titleField = new ListGridField("title", "Title");
		ListGridField runField = new ListGridField("runButton", "Run");
		runField.setWidth(50);
		this.setFields(idField, runField);
		this.setShowRecordComponents(true);          
        this.setShowRecordComponentsByCell(true);
        this.setShowHeader(false);
        
        // Tooltip
        idField.setShowHover(true);
        idField.setHoverCustomizer(new HoverCustomizer() {
			@Override
			public String hoverHTML(Object value, ListGridRecord record, int rowNum,
					int colNum) {
				return record.getAttributeAsString("title");
			}
		});
        
        createContextMenu();
	}
	
	@Override
	protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
		String fieldName = this.getFieldName(colNum);
        if (fieldName.equals("runButton")) {
			final IButton button = new IButton();  
	        button.setHeight(18);  
	        button.setWidth(50);                      
	        button.setTitle("Run");  
	        button.addClickHandler(new ClickHandler() {  
	            @Override
				public void onClick(ClickEvent event) {
	            	ProcessRecord processRecord = (ProcessRecord)record;
	            	RunProcessWindow runWindow = new RunProcessWindow((DetailedProcessDescriptor) processRecord.getProcessDescriptor());
	            	runWindow.show();
	            }  
	        });  
	        return button;
        }
        
        return null;
	}
	
	/**
	 * TODO: document
	 */
	public final void createContextMenu() {
		Menu menu = new Menu();
		menu.setShowShadow(true);
		menu.setShadowDepth(10);
		
		final MenuItem remove = new MenuItem("Remove");
		remove.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				if (getSelectedRecord() != null) {
                    RunProcessListGrid.this.removeData(getSelectedRecord());
                }
			}
		});

		menu.setItems(remove);

		setContextMenu(menu);
	}
	
}
