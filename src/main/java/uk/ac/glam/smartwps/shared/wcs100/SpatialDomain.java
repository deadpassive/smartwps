package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

public class SpatialDomain implements Serializable {

	private static final long serialVersionUID = 4932748932849819956L;
	private ArrayList<BoundsSerializable> envelopes;
	private ArrayList<Grid> grids;

	public void setEnvelopes(ArrayList<BoundsSerializable> envelopes) {
		this.envelopes = envelopes;
	}
	
	public ArrayList<BoundsSerializable> getEnvelopes() {
		return envelopes;
	}

	public void setGrids(ArrayList<Grid> grids) {
		this.grids = grids;
	}
	
	public ArrayList<Grid> getGrids() {
		return grids;
	}

}
