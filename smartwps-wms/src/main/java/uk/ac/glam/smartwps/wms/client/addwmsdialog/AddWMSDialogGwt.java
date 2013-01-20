package uk.ac.glam.smartwps.wms.client.addwmsdialog;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import uk.ac.glam.smartwps.base.client.ui.CloseCaption;
import uk.ac.glam.smartwps.base.client.ui.WaterMarkedTextBox;
import uk.ac.glam.smartwps.wms.client.AddWMSPresenter;
import uk.ac.glam.smartwps.wms.shared.WMSLayer;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

/**
 * Native GWT implementation of the Add WMS dialog.
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
	
	/**
	 * The URL text input
	 */
	@UiField
	WaterMarkedTextBox urlInput;
	/**
	 * Pager for the data grid
	 */
	@UiField(provided = true)
	SimplePager pager;
	/**
	 * Data grid for displaying WMS layers
	 */
	@UiField(provided = true)
	DataGrid<WMSLayer> dataGrid;
	
	private ListDataProvider<WMSLayer> dataProvider;

	private MultiSelectionModel<WMSLayer> selectionModel;

	/**
	 * TODO: document
	 */
	public AddWMSDialogGwt() {
		super(new CloseCaption());
		CloseCaption caption = (CloseCaption) getCaption();
		caption.doOnClose(new Command() {
			
			@Override
			public void execute() {
				hide();
			}
		});
		setText("Add WMS Layers");
		
		getElement().getStyle().setZIndex(1000000);
		
		createDataGrid();

		setWidget(uiBinder.createAndBindUi(this));
		
		urlInput.setText("http://localhost:8080/geoserver/ows?service=wms&version=1.3.0&request=GetCapabilities");

//		setSize("500px", "500px");
	}
	
	private void createDataGrid() {
		dataGrid = new DataGrid<WMSLayer>();
		dataGrid.setWidth("100%");
		dataGrid.setEmptyTableWidget(new Label("No layers"));
		
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(dataGrid);
		
		selectionModel = new MultiSelectionModel<WMSLayer>();
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
	
	/**
	 * TODO: document
	 * @param event
	 */
	@UiHandler("addButton")
	void addPressed(@SuppressWarnings("unused") ClickEvent event) {
		Set<WMSLayer> selected = selectionModel.getSelectedSet();
		presenter.addLayers(selected);
	}
}
