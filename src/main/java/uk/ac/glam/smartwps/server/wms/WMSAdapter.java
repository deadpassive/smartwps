package uk.ac.glam.smartwps.server.wms;

import java.util.ArrayList;
import java.util.List;

import org.geotools.data.ows.CRSEnvelope;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.StyleImpl;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

public class WMSAdapter {
    
    private WMSAdapter() {}

	public static WMSLayer layerAdapter(Layer layer) {
		WMSLayer wmsLayer = new WMSLayer();
		
		wmsLayer.setTitle(layer.getTitle());
		wmsLayer.setName(layer.getName());
		wmsLayer.setLayerAbstract(layer.get_abstract());
		wmsLayer.setBbox(latLonBoundingBoxAdapter(layer.getLatLonBoundingBox()));
		wmsLayer.setStyles(stylesAdapter(layer.getStyles()));
		//wmsLayer.setCrsList(layer.getSrs().toArray(new String[0]));
		
		return wmsLayer;
	}
	
	private static List<String> stylesAdapter(List<StyleImpl> styles) {
		ArrayList<String> stylesList = new ArrayList<String>();
        for (StyleImpl styleImpl : styles) {
			stylesList.add(styleImpl.getName());
		}
		return stylesList;
	}

	public static BoundsSerializable latLonBoundingBoxAdapter(CRSEnvelope env) {
		BoundsSerializable bbox = new BoundsSerializable();
		
		bbox.setMaxX(env.getMaxX());
		bbox.setMaxY(env.getMaxY());
		bbox.setMinX(env.getMinX());
		bbox.setMinY(env.getMinY());
		// TODO: SHOULDN'T BE HARD CODED!!
		bbox.setProjection("EPSG:4326");
		
		return bbox;
	}
}
