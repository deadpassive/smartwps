package uk.ac.glam.smartwps.shared.wcs111;

import uk.ac.glam.smartwps.shared.Data;
import uk.ac.glam.smartwps.shared.wms.WMSLayer;

/**
 * This class contains coverage info and an associated WMS layer for visualisation
 * 
 * @author Jon
 *
 */
public class WCSCoverage extends Data {

	private static final long serialVersionUID = 2652868587138031594L;
	private CoverageDescription coverageDescription;
	private WMSLayer wmsLayer;
	
	public WCSCoverage(){}
	
	public WCSCoverage(CoverageDescription coverageOffering, WMSLayer wmsLayer) {
		this.coverageDescription = coverageOffering;
		this.wmsLayer = wmsLayer;
	}

	public CoverageDescription getCoverageDescription() {
		return coverageDescription;
	}
	
	public void setCoverageDescription(CoverageDescription coverageDescription) {
		this.coverageDescription = coverageDescription;
	}
	
	public WMSLayer getWmsLayer() {
		return wmsLayer;
	}
	
	public void setWmsLayer(WMSLayer wmsLayer) {
		this.wmsLayer = wmsLayer;
	}
	
	public String getIdentifier() {
		return coverageDescription.getIdentifier();
	}
	
}
