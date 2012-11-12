package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

@SuppressWarnings("serial")
public class SpatialDomain implements Serializable {

	private List<BoundsSerializable> boundingBoxList = new ArrayList<BoundsSerializable>();
	private String gridBaseCRS;
	
	public void addBoundingBox(BoundsSerializable bbox) {
		boundingBoxList.add(bbox);
	}

	public void setGridBaseCRS(String gridBaseCRS) {
		this.gridBaseCRS = gridBaseCRS;
	}

	public String getGridBaseCRS() {
		return gridBaseCRS;
	}

	/**
	 * Returns a bounding box for the given CRS
	 * @param crs
	 * @return
	 */
	public BoundsSerializable getBoundingBox(String crs) {
        for (BoundsSerializable bbox : boundingBoxList) {
            if (bbox.getProjection().equalsIgnoreCase(crs)) {
                return bbox;
            }
        }
		return null;
	}

	public List<BoundsSerializable> getBoundingBoxList() {
		return new ArrayList<BoundsSerializable>(boundingBoxList);
	}

}
