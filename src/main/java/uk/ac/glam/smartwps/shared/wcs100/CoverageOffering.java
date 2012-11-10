package uk.ac.glam.smartwps.shared.wcs100;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

public class CoverageOffering extends CoverageOfferingBrief {

	private static final long serialVersionUID = -971144607136542365L;
	private DomainSet domainSet;
	private ArrayList<String> requestCRSs;
	private ArrayList<String> responseCRSs;
	private static final Logger LOGGER = Logger.getLogger("smartwps.client");

	public void setDomainSet(DomainSet domainSet) {
		this.domainSet = domainSet;
	}

	public DomainSet getDomainSet() {
		return domainSet;
	}

	public void setRequestCRSs(ArrayList<String> requestCRSs) {
		this.requestCRSs = requestCRSs;
	}

	public void setResponseCRSs(ArrayList<String> responseCRSs) {
		this.responseCRSs = responseCRSs;
	}

	public ArrayList<String> getRequestCRSs() {
		return requestCRSs;
	}

	public ArrayList<String> getResponseCRSs() {
		return responseCRSs;
	}
	
	public double[] getNativeResolution(String crs) {
		LOGGER.info("Getting native resolution");
		RectifiedGrid rGrid = getRectifiedGridForCRS(crs);
		if (rGrid == null)
			return null;
		
		double[] offsetX = rGrid.getOffsetVectors().get(0);
		LOGGER.info("Offset Vector: " + offsetX[0] + "," + offsetX[1]);
		double resX = Math.max(Math.abs(offsetX[0]), Math.abs(offsetX[1]));
		LOGGER.info("ResS: " + resX);
		
		double[] offsetY = rGrid.getOffsetVectors().get(1);
		LOGGER.info("Offset Vector: " + offsetY[0] + "," + offsetY[1]);
		double resY = Math.max(Math.abs(offsetY[0]), Math.abs(offsetY[1]));
		LOGGER.info("ResS: " + resY);
		
		double[] result = {resX, resY};
		return result;
		
	}

	
	public RectifiedGrid getRectifiedGridForCRS(String crs) {
		ArrayList<Grid> grids = getDomainSet().getSpatialDomain().getGrids();
		for (Iterator<Grid> iterator = grids.iterator(); iterator.hasNext();) {
			Grid grid = iterator.next();
			String gridCRS = grid.getSRSName();
			// Not sure if the envelope is necessarily associated with the Grid, but it seems to be most of the time.
			if (gridCRS == null) 	// Grid doesn't specify a SRS, lets see if the Envelope has one
				gridCRS = getDomainSet().getSpatialDomain().getEnvelopes().get(0).getProjection();
			if ((grid instanceof RectifiedGrid) && (gridCRS.equals(crs))) {
				return (RectifiedGrid)grid;
			}
		}
		return null;
	}
	
	public String createWCSKVPRequest(BoundsSerializable boundingBox, String responseCRS, double resX, double resY) {
		String kvp = serviceURL + "?SERVICE=WCS&VERSION=1.0.0&REQUEST=GetCoverage&COVERAGE=" + name;
		kvp += "&BBOX=" + boundingBox.getMinX() + "," + boundingBox.getMinY() + "," + boundingBox.getMaxX() + "," + boundingBox.getMaxY();
		kvp += "&CRS=" + boundingBox.getProjection();
		if (responseCRS != null)
			kvp += "&RESPONSE_CRS=" + responseCRS;
		kvp += "&FORMAT=geotiff";
		kvp += "&RESX=" + resX + "&RESY=" + resY;
		return kvp;
	}
	
}
