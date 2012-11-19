package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;
import java.util.List;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureTypeBase;

public class WFSGetCapabilitiesResponse implements ServiceResponse {

	private List<WFSFeatureTypeBase> wfsLayers;

	public List<WFSFeatureTypeBase> getWFSLayers() {
		return new ArrayList<WFSFeatureTypeBase>(wfsLayers);
	}

	public void setWFSLayers(List<WFSFeatureTypeBase> wfsLayers) {
		this.wfsLayers = new ArrayList<WFSFeatureTypeBase>(wfsLayers);
	}
}
