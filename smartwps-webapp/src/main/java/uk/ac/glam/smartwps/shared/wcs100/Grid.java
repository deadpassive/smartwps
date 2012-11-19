package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("serial")
public class Grid implements Serializable {

	private int dimension;
	private GridEnvelope limits;
	private List<String> axisNames;
	private String srsName;
	private ArrayList<double[]> offsetVectors;

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public int getDimension() {
		return dimension;
	}

	public void setLimits(GridEnvelope limits) {
		this.limits = limits;
	}
	
	public GridEnvelope getLimits() {
		return limits;
	}

	public void setAxisNames(List<String> axisNames) {
		this.axisNames = new ArrayList<String>(axisNames);
	}
	
	public ArrayList<String> getAxisNames() {
		return new ArrayList<String>(axisNames);
	}

	public void setSRSName(String srsName) {
		this.srsName = srsName;
	}

	public String getSRSName() {
		return srsName;
	}

	public void setOffsetVectors(List<double[]> offsetVectors) {
		this.offsetVectors = new ArrayList<double[]>(offsetVectors);
	}
	
	public List<double[]> getOffsetVectors() {
		return new ArrayList<double[]>(offsetVectors);
	}

}
