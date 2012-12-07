package uk.ac.glam.smartwps.client;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.smartgwt.client.widgets.HTMLPane;
import uk.ac.glam.smartwps.client.datatree.DataTree;
import uk.ac.glam.smartwps.client.map.OLMap;
import uk.ac.glam.smartwps.client.wps.RunProcessListGrid;

/**
 * View interface. Extends IsWidget so a view impl can easily provide
 * its container widget.
 *
 * @author Jon Britton
 */
public interface AppLayout extends IsWidget {
	
    /**
     * TODO: document
     * @return
     */
    public HTMLPane getInfoPane();

    /**
     * TODO: document
     * @return
     */
    public OLMap getMap();

    /**
     * TODO: document
     * @return
     */
    public DataTree getDataTree();

    /**
     * TODO: document
     * @return
     */
    public RunProcessListGrid getRunProcessList();
	
	/**
	 * TODO: document
	 * @return
	 */
	public HasOneWidget getResultsHolder();
}