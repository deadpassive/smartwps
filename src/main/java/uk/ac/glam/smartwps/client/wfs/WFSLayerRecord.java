package uk.ac.glam.smartwps.client.wfs;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureTypeBase;

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
	 * @return the WFS feature type object represented by this record
	 */
	public WFSFeatureTypeBase getWFSFeatureType() {
		return wfsFeatureType;
	}
}
