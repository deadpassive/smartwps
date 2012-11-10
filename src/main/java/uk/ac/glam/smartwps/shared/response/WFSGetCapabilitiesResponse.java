package uk.ac.glam.smartwps.shared.response;

import java.util.ArrayList;

import uk.ac.glam.smartwps.shared.wfs.WFSFeatureTypeBase;

public class WFSGetCapabilitiesResponse implements ServiceResponse {

	private static final long serialVersionUID = -2297984023323979354L;
	private ArrayList<WFSFeatureTypeBase> wfsLayers;

	public ArrayList<WFSFeatureTypeBase> getWFSLayers() {
		return wfsLayers;
	}

	public void setWFSLayers(ArrayList<WFSFeatureTypeBase> wfsLayers) {
		this.wfsLayers = wfsLayers;
	}
}
