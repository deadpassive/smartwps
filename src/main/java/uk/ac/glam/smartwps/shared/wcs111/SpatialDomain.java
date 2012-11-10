package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

public class SpatialDomain implements Serializable {

	private static final long serialVersionUID = -5513550021121563943L;
	private ArrayList<BoundsSerializable> boundingBoxList = new ArrayList<BoundsSerializable>();
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
		for (Iterator<BoundsSerializable> iterator = boundingBoxList.iterator(); iterator.hasNext();) {
			BoundsSerializable bbox = iterator.next();
			if (bbox.getProjection().equals(crs))
				return bbox;
		}
		return null;
	}

	public ArrayList<BoundsSerializable> getBoundingBoxList() {
		return boundingBoxList;
	}

}
