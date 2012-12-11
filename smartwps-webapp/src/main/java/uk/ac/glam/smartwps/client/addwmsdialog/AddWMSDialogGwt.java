package uk.ac.glam.smartwps.client.addwmsdialog;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import uk.ac.glam.smartwps.client.widget.WaterMarkedTextBox;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class AddWMSDialogGwt extends DialogBox implements AddWMSPresenter.Display {

	private static AddWMSDialogGwtUiBinder uiBinder = GWT.create(AddWMSDialogGwtUiBinder.class);

	/**
	 * TODO: document
	 */
	interface AddWMSDialogGwtUiBinder extends UiBinder<Widget, AddWMSDialogGwt> {}

	private AddWMSPresenter presenter;
	
	@UiField
	WaterMarkedTextBox urlInput;
	@UiField(provided = true)
	SimplePager pager;
	@UiField(provided = true)
	DataGrid<WMSLayer> dataGrid;
	
	private ListDataProvider<WMSLayer> dataProvider;

	/**
	 * TODO: document
	 */
	public AddWMSDialogGwt() {
		setText("Add WMS Layers");
				
		getElement().getStyle().setZIndex(1000000);
		
		dataGrid = new DataGrid<WMSLayer>();
		dataGrid.setWidth("100%");
		dataGrid.setEmptyTableWidget(new Label("No layers"));
		
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(dataGrid);
		
		final MultiSelectionModel<WMSLayer> selectionModel = new MultiSelectionModel<WMSLayer>();
		dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager.<WMSLayer> createCheckboxManager());

		Column<WMSLayer, Boolean> checkColumn = new Column<WMSLayer, Boolean>(new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(WMSLayer object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};
		dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		dataGrid.setColumnWidth(checkColumn, 40, Unit.PX);
		
		TextColumn<WMSLayer> nameColumn = new TextColumn<WMSLayer>() {
			
			@Override
			public String getValue(WMSLayer wmsLayer) {
				return wmsLayer.getName();
			}
		};
		nameColumn.setSortable(true);
		dataGrid.addColumn(nameColumn, "Name");
		
		TextColumn<WMSLayer> titleColumn = new TextColumn<WMSLayer>() {

			@Override
			public String getValue(WMSLayer wmsLayer) {
				return wmsLayer.getTitle();
			}
		};
		titleColumn.setSortable(true);
		dataGrid.addColumn(titleColumn, "Title");
		
		TextColumn<WMSLayer> absColumn = new TextColumn<WMSLayer>() {
			
			@Override
			public String getValue(WMSLayer wmsLayer) {
				return wmsLayer.getAbstract();
			}
		};
		absColumn.setSortable(true);
		dataGrid.addColumn(absColumn, "Description");

		
		
		dataProvider = new ListDataProvider<WMSLayer>();
		dataProvider.addDataDisplay(dataGrid);

		setWidget(uiBinder.createAndBindUi(this));
				
		ListHandler<WMSLayer> sortHandler = new ListHandler<WMSLayer>(dataProvider.getList());
		
		sortHandler.setComparator(nameColumn, new Comparator<WMSLayer>() {
			
			@Override
			public int compare(WMSLayer layer1, WMSLayer layer2) {
				return layer1.getName().compareTo(layer2.getName());
			}
		});
		sortHandler.setComparator(titleColumn, new Comparator<WMSLayer>() {

			@Override
			public int compare(WMSLayer layer1, WMSLayer layer2) {
				return layer1.getTitle().compareTo(layer2.getTitle());
			}
		});
		sortHandler.setComparator(absColumn, new Comparator<WMSLayer>() {

			@Override
			public int compare(WMSLayer layer1, WMSLayer layer2) {
				return layer1.getAbstract().compareTo(layer2.getAbstract());
			}
		});
		
		dataGrid.addColumnSortHandler(sortHandler);
		
//		setSize("500px", "500px");
	}

	@Override
	public void showDialog() {
		center();
	}
	
	@Override
	public void setPresenter(AddWMSPresenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void setWMSLayers(List<WMSLayer> wmsLayers) {
		dataProvider.getList().clear();
		dataProvider.getList().addAll(wmsLayers);
//		cellTable.setRowData(wmsLayers);
	}
	
	@Override
	public void doFailure(String message) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * TODO: document
	 * @param event
	 */
	@SuppressWarnings("unused")
	@UiHandler("goButton")
	void goPressed(ClickEvent event) {
		presenter.retrieveWMSLayer(urlInput.getText());
	}
}
