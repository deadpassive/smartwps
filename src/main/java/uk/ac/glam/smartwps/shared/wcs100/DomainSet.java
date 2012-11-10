package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;

public class DomainSet implements Serializable {

	private static final long serialVersionUID = 7352505565173832869L;
	private SpatialDomain spatialDoman;

	public void setSpatialDomain(SpatialDomain spatialDomain) {
		this.spatialDoman = spatialDomain;
	}
	
	public SpatialDomain getSpatialDomain() {
		return spatialDoman;
	}

}
