package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class DomainSet implements Serializable {

	private SpatialDomain spatialDoman;

	/**
	 * TODO: document
	 * @param spatialDomain
	 */
	public void setSpatialDomain(SpatialDomain spatialDomain) {
		this.spatialDoman = spatialDomain;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public SpatialDomain getSpatialDomain() {
		return spatialDoman;
	}

}
