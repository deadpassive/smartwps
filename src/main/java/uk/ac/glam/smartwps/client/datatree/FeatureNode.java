package uk.ac.glam.smartwps.client.datatree;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.VectorOptions;
import org.gwtopenmaps.openlayers.client.protocol.CRUDOptions.Callback;
import org.gwtopenmaps.openlayers.client.protocol.Response;
import org.gwtopenmaps.openlayers.client.protocol.WFSProtocol;
import org.gwtopenmaps.openlayers.client.protocol.WFSProtocolCRUDOptions;
import org.gwtopenmaps.openlayers.client.protocol.WFSProtocolOptions;

import uk.ac.glam.smartwps.client.ClientUtils;
import uk.ac.glam.smartwps.client.SmartWPS;
import uk.ac.glam.smartwps.shared.wfs.WFSFeatureType;

import com.smartgwt.client.widgets.menu.Menu;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class FeatureNode extends DataTreeNode {

	private WFSFeatureType featureType;

	// private Vector wfsLayer;

	/**
	 * TODO: document
	 * @param featureType
	 */
	public FeatureNode(WFSFeatureType featureType) {
		this.setFeatureType(featureType);
		setAttribute("type", "WFS");
		// We'll use typename because it's a bit more descriptive
		setAttribute("name", featureType.getTypeName());
		addLayerToMap();
		setIcon("wfsicon.png");
		setAttribute("localname", featureType.getTypeName());
	}

	@Override
	void addLayerToMap() {
		VectorOptions vectorOptions = new VectorOptions();
		mapLayer = new Vector(featureType.getName(), vectorOptions);

		WFSProtocolOptions wfsProtocolOptions = new WFSProtocolOptions(
				featureType.getServiceURL(), featureType.getNamespaceURI(), featureType.getName());
		wfsProtocolOptions.setSrsName("EPSG:4326");
		wfsProtocolOptions.setVersion(featureType.getWfsVersion());
		//wfsProtocolOptions.getJSObject().setProperty("featurePrefix", "sf");
		//wfsProtocolOptions.getJSObject().setProperty("schema", "http://li199-25.members.linode.com:8080/geoserver/wfs?service=wfs&version=1.0.0&request=DescribeFeatureType&typename=sf:roads");

		WFSProtocol wfsProtocol = new WFSProtocol(wfsProtocolOptions);
		// WFS Protocol is asynchronous, so we need to use a callback
		Callback callback = new Callback() {
			@Override
			public void computeResponse(Response response) {
				try {
					VectorFeature[] features = response.getFeatures();
					//for (VectorFeature feature : features) {
						// Apply style depending on any attribute of you feature
						//Style style = new Style();
						//style.setPointRadius(5);
						//style.setFillColor("#00FF00");
						//feature.setStyle(style);
					//}
					((Vector) mapLayer).addFeatures(features);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		WFSProtocolCRUDOptions options = new WFSProtocolCRUDOptions(callback);
		wfsProtocol.read(options);
		
		SmartWPS.getSmartWPS().getMap().addLayer(mapLayer);
	}

	/**
	 * TODO: document
	 * @param featureType
	 */
	public void setFeatureType(WFSFeatureType featureType) {
		this.featureType = featureType;
	}

	/**
	 * @return the WFS feature type associated with this node
	 */
	public WFSFeatureType getFeatureType() {
		return featureType;
	}

	@Override
	public Bounds getExtent() {
		return ClientUtils.createBounds(featureType.getBounds());
	}

	@Override
	public Menu getContextMenu() {
		if (this.contextMenu == null)
			this.contextMenu = new DataTreeNodeContextMenu(this);
		return contextMenu;
	}

}
