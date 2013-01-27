package uk.ac.glam.smartwps.wfs.client;

import uk.ac.glam.smartwps.wfs.shared.WFSFeatureTypeBase;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class WFSLayerRecord extends ListGridRecord {

	private WFSFeatureTypeBase wfsFeatureType;

	/**
	 * TODO: document
	 * @param wfsFeatureType
	 */
	public WFSLayerRecord(WFSFeatureTypeBase wfsFeatureType) {
		this.wfsFeatureType = wfsFeatureType;
		this.setAttribute("typename", wfsFeatureType.getTypeName());
		this.setAttribute("title", wfsFeatureType.getTitle());
		this.setAttribute("abstract", wfsFeatureType.getAbstract());
		//this.setAttribute("namespaceuri", wfsFeatureType.getNamespaceURI());
		this.setAttribute("name", wfsFeatureType.getName());
	}

	/**
	 * TODO: document
	 * @return
	 */
	public WFSFeatureTypeBase getWFSFeatureType() {
		return wfsFeatureType;
	}
}
