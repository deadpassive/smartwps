package uk.ac.glam.smartwps.wcs.shared.v100;

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

	private List<BoundsSerializable> envelopes;
	private List<Grid> grids;

	/**
	 * TODO: document
	 * @param envelopes
	 */
	public void setEnvelopes(List<BoundsSerializable> envelopes) {
		this.envelopes = new ArrayList<BoundsSerializable>(envelopes);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public List<BoundsSerializable> getEnvelopes() {
		return new ArrayList<BoundsSerializable>(envelopes);
	}

	/**
	 * TODO: document
	 * @param grids
	 */
	public void setGrids(List<Grid> grids) {
		this.grids = new ArrayList<Grid>(grids);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public List<Grid> getGrids() {
		return new ArrayList<Grid>(grids);
	}

}
