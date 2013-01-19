package uk.ac.glam.smartwps.wcs.shared.v111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class SpatialDomain implements Serializable {

	private List<BoundsSerializable> boundingBoxList = new ArrayList<BoundsSerializable>();
	private String gridBaseCRS;
	
	/**
	 * TODO: document
	 * @param bbox
	 */
	public void addBoundingBox(BoundsSerializable bbox) {
		boundingBoxList.add(bbox);
	}

	/**
	 * TODO: document
	 * @param gridBaseCRS
	 */
	public void setGridBaseCRS(String gridBaseCRS) {
		this.gridBaseCRS = gridBaseCRS;
	}

	/**
	 * TODO: document
	 * @return
	 */
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

	/**
	 * TODO: document
	 * @return
	 */
	public List<BoundsSerializable> getBoundingBoxList() {
		return new ArrayList<BoundsSerializable>(boundingBoxList);
	}

}
