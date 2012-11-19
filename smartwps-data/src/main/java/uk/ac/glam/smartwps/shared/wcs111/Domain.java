package uk.ac.glam.smartwps.shared.wcs111;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Domain implements Serializable {
	
	private SpatialDomain spatialDomain;

	public void setSpatialDomain(SpatialDomain spatialDomain) {
		this.spatialDomain = spatialDomain;
	}

	public SpatialDomain getSpatialDomain() {
		return spatialDomain;
	}
}
