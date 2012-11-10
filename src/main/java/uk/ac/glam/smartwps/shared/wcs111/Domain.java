package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;

public class Domain implements Serializable {

	private static final long serialVersionUID = -4944620242225638836L;
	
	private SpatialDomain spatialDomain;

	public void setSpatialDomain(SpatialDomain spatialDomain) {
		this.spatialDomain = spatialDomain;
	}

	public SpatialDomain getSpatialDomain() {
		return spatialDomain;
	}
}
