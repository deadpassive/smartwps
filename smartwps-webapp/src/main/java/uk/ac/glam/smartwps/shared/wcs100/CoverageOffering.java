package uk.ac.glam.smartwps.shared.wcs100;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

@SuppressWarnings("serial")
public class CoverageOffering extends CoverageOfferingBrief {

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

	public void setRequestCRSs(List<String> requestCRSs) {
		this.requestCRSs = new ArrayList<String>(requestCRSs);
	}

	public void setResponseCRSs(List<String> responseCRSs) {
		this.responseCRSs = new ArrayList<String>(responseCRSs);
	}

	public ArrayList<String> getRequestCRSs() {
		return new ArrayList<String>(requestCRSs);
	}

	public ArrayList<String> getResponseCRSs() {
		return new ArrayList<String>(responseCRSs);
	}
	
	public double[] getNativeResolution(String crs) {
		LOGGER.info("Getting native resolution");
		RectifiedGrid rGrid = getRectifiedGridForCRS(crs);
		if (rGrid == null) {
            return null;
        }
		
		double[] offsetX = rGrid.getOffsetVectors().get(0);
		LOGGER.log(Level.INFO, "Offset Vector: {0},{1}", new Object[]{offsetX[0], offsetX[1]});
		double resX = Math.max(Math.abs(offsetX[0]), Math.abs(offsetX[1]));
		LOGGER.log(Level.INFO, "ResS: {0}", resX);
		
		double[] offsetY = rGrid.getOffsetVectors().get(1);
		LOGGER.log(Level.INFO, "Offset Vector: {0},{1}", new Object[]{offsetY[0], offsetY[1]});
		double resY = Math.max(Math.abs(offsetY[0]), Math.abs(offsetY[1]));
		LOGGER.log(Level.INFO, "ResS: {0}", resY);
		
		double[] result = {resX, resY};
		return result;
		
	}
	
	public RectifiedGrid getRectifiedGridForCRS(String crs) {
		List<Grid> grids = getDomainSet().getSpatialDomain().getGrids();
        for (Grid grid : grids) {
            String gridCRS = grid.getSRSName();
			// Not sure if the envelope is necessarily associated with the Grid, but it seems to be most of the time.
			if (gridCRS == null) {
                // Grid doesn't specify a SRS, lets see if the Envelope has one
                gridCRS = getDomainSet().getSpatialDomain().getEnvelopes().get(0).getProjection();
            }
			if ((grid instanceof RectifiedGrid) && (gridCRS.equals(crs))) {
				return (RectifiedGrid)grid;
			}
        }
		return null;
	}
	
	public String createWCSKVPRequest(BoundsSerializable boundingBox, String responseCRS, double resX, double resY) {
        StringBuilder kvp = new StringBuilder(serviceURL)
                .append("?SERVICE=WCS&VERSION=1.0.0&REQUEST=GetCoverage&COVERAGE=").append(name)
                .append("&BBOX=").append(boundingBox.getMinX()).append(",")
                .append(boundingBox.getMinY()).append(",")
                .append(boundingBox.getMaxX()).append(",")
                .append(boundingBox.getMaxY())
                .append("&CRS=").append(boundingBox.getProjection());
		if (responseCRS != null) {
            kvp.append("&RESPONSE_CRS=").append(responseCRS);
        }
		kvp.append("&FORMAT=geotiff").append("&RESX=").append(resX).append("&RESY=").append(resY);
		return kvp.toString();
	}
	
}
