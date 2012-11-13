package uk.ac.glam.smartwps.shared.wms;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.Data;
import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

/**
 * TODO: document.
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class WMSLayer extends Data {

	private String name;
	private String title;
	private String layerAbstract;
	private List<String> keywords;
	private BoundsSerializable bbox;
    private List<String> crsList;
	private List<String> styles;
	
	/**
	 * TODO: document
	 * @return
	 */
	public BoundsSerializable getBbox() {
		return bbox;
	}
	
    /**
     * TODO: document.
     * @param bbox 
     */
	public void setBbox(BoundsSerializable bbox) {
		this.bbox = bbox;
	}
	
    /**
     * TODO: document.
     * @return 
     */
	public String getName() {
		return name;
	}
	
    /**
     * TODO: document.
     * @param name 
     */
	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * TODO: document.
     * @return 
     */
	public String getTitle() {
		return title;
	}
	
    /**
     * TODO: document.
     * @param title 
     */
	public void setTitle(String title) {
		this.title = title;
	}
	
    /**
     * TODO: document.
     * @return 
     */
	public String getLayerAbstract() {
		return layerAbstract;
	}
	
    /**
     * TODO: document.
     * @param layerAbstract 
     */
	public void setLayerAbstract(String layerAbstract) {
		this.layerAbstract = layerAbstract;
	}
	
    /**
     * TODO: document.
     * @return 
     */
	public ArrayList<String> getKeywords() {
		return new ArrayList<>(keywords);
	}
	
    /**
     * TODO: document.
     * @param keywords 
     */
	public void setKeywords(ArrayList<String> keywords) {
        this.keywords = new ArrayList<>(keywords);
	}
	
    /**
     * TODO: document.
     * @param crsList 
     */
	public void setCrsList(List<String> crsList) {
		this.crsList = new ArrayList<>(crsList);
	}
	
    /**
     * TODO: document.
     * @return 
     */
	public ArrayList<String> getCrsList() {
		return new ArrayList<>(crsList);
	}
	
    /**
     * TODO: document.
     * @param styles 
     */
	public void setStyles(List<String> styles) {
        this.styles = new ArrayList<>(styles);
	}
	
    /**
     * TODO: document.
     * @return 
     */
	public List<String> getStyles() {
		return new ArrayList<>(styles);
	}
	
}
