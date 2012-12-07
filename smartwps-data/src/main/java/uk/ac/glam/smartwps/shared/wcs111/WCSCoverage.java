package uk.ac.glam.smartwps.shared.wcs111;

import uk.ac.glam.smartwps.shared.Data;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

/**
 * This class contains coverage info and an associated WMS layer for visualisation.
 * 
 * @author Jon
 */
public class WCSCoverage extends Data {

	private CoverageDescription coverageDescription;
	private WMSLayer wmsLayer;
	
	/**
	 * TODO: document
	 */
	public WCSCoverage(){}
	
	/**
	 * TODO: document
	 * @param coverageOffering
	 * @param wmsLayer
	 */
	public WCSCoverage(CoverageDescription coverageOffering, WMSLayer wmsLayer) {
		this.coverageDescription = coverageOffering;
		this.wmsLayer = wmsLayer;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public CoverageDescription getCoverageDescription() {
		return coverageDescription;
	}
	
	/**
	 * TODO: document
	 * @param coverageDescription
	 */
	public void setCoverageDescription(CoverageDescription coverageDescription) {
		this.coverageDescription = coverageDescription;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public WMSLayer getWmsLayer() {
		return wmsLayer;
	}
	
	/**
	 * TODO: document
	 * @param wmsLayer
	 */
	public void setWmsLayer(WMSLayer wmsLayer) {
		this.wmsLayer = wmsLayer;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public String getIdentifier() {
		return coverageDescription.getIdentifier();
	}
	
}
