package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

@SuppressWarnings("serial")
public class SpatialDomain implements Serializable {

	private List<BoundsSerializable> envelopes;
	private List<Grid> grids;

	public void setEnvelopes(List<BoundsSerializable> envelopes) {
		this.envelopes = new ArrayList<BoundsSerializable>(envelopes);
	}
	
	public List<BoundsSerializable> getEnvelopes() {
		return new ArrayList<BoundsSerializable>(envelopes);
	}

	public void setGrids(List<Grid> grids) {
		this.grids = new ArrayList<Grid>(grids);
	}
	
	public List<Grid> getGrids() {
		return new ArrayList<Grid>(grids);
	}

}
