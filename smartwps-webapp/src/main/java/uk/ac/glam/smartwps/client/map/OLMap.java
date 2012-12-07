package uk.ac.glam.smartwps.client.map;

import com.google.gwt.user.client.ui.Composite;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.control.Control;
import org.gwtopenmaps.openlayers.client.control.MousePosition;
import org.gwtopenmaps.openlayers.client.control.Navigation;
import org.gwtopenmaps.openlayers.client.control.PanZoomBar;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.util.JObjectArray;
import org.gwtopenmaps.openlayers.client.util.JSObject;

/**
 * TODO: document.
 * 
 * @author steve@spiffymap.net
 */
public class OLMap extends Composite {
    
    private final MapWidget mapWidget;

    /**
     * TODO: document
     */
    public OLMap() {
        mapWidget = createMap();
        
        initWidget(mapWidget);
    }
    
    private static MapWidget createMap() {
//		LOGGER.info("Creating Map");
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

    /**
     * TODO: document
     * @return
     */
    public Map getOLMap() {
        return mapWidget.getMap();
    }

    /**
     * TODO: document
     * @param mapLayer
     */
    public void removeLayer(Layer mapLayer) {
        mapWidget.getMap().removeLayer(mapLayer);
    }

    /**
     * TODO: document
     * @param layer
     * @param i
     */
    public void setLayerIndex(Layer layer, int i) {
        mapWidget.getMap().setLayerIndex(layer, i);
    }

    /**
     * TODO: document
     * @param extent
     */
    public void zoomToExtent(Bounds extent) {
        mapWidget.getMap().zoomToExtent(extent);
    }

    /**
     * TODO: document
     * @param control
     */
    public void removeControl(Control control) {
        mapWidget.getMap().removeControl(control);
    }

    /**
     * TODO: document
     * @param control
     */
    public void addControl(Control control) {
        mapWidget.getMap().addControl(control);
    }

    /**
     * TODO: document
     * @param mapLayer
     */
    public void addLayer(Layer mapLayer) {
        mapWidget.getMap().addLayer(mapLayer);
    }

    /**
     * TODO: document
     * @return
     */
    public String getProjection() {
        return mapWidget.getMap().getProjection();
    }
}
