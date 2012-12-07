package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
@SuppressWarnings("serial")
public class Domain implements Serializable {
	
	private SpatialDomain spatialDomain;

	/**
	 * TODO: document
	 * @param spatialDomain
	 */
	public void setSpatialDomain(SpatialDomain spatialDomain) {
		this.spatialDomain = spatialDomain;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public SpatialDomain getSpatialDomain() {
		return spatialDomain;
	}
}
