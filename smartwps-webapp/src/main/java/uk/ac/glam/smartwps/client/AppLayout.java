package uk.ac.glam.smartwps.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;

import org.gwtopenmaps.openlayers.client.Map;
import uk.ac.glam.smartwps.client.datatree.DataTree;
import uk.ac.glam.smartwps.client.wps.RunProcessListGrid;

/**
 * View interface. Extends IsWidget so a view impl can easily provide
 * its container widget.
 *
 * @author drfibonacci
 */
public interface AppLayout extends IsWidget {
    public HTMLPane getInfoPane();

    public Map getMap();

    public DataTree getDataTree();

    public RunProcessListGrid getRunProcessList();
	
	public HasOneWidget getResultsHolder();
}