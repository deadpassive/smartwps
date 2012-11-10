package uk.ac.glam.smartwps.shared.wms;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.Data;
import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

@SuppressWarnings("serial")
public class WMSLayer extends Data {

	String name;
	String title;
	String layerAbstract;
	ArrayList<String> keywords;
//	String serviceURL;
	BoundsSerializable bbox;
	String[] crsList;
	private List<String> styles;
	
	public BoundsSerializable getBbox() {
		return bbox;
	}
	
	public void setBbox(BoundsSerializable bbox) {
		this.bbox = bbox;
	}
	
//	public String getServiceURL() {
//		return serviceURL;
//	}
//	
//	public void setServiceURL(String serviceURL) {
//		this.serviceURL = serviceURL;
//	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLayerAbstract() {
		return layerAbstract;
	}
	
	public void setLayerAbstract(String layerAbstract) {
		this.layerAbstract = layerAbstract;
	}
	
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	
	public void setCrsList(String[] crsList) {
		this.crsList = crsList;
	}
	
	public String[] getCrsList() {
		return crsList;
	}
	
	public void setStyles(List<String> styles) {
		this.styles = styles;
	}
	
	public List<String> getStyles() {
		return styles;
	}
	
}
