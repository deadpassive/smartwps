package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;

public class Grid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 958746403827470358L;
	private int dimension;
	private GridEnvelope limits;
	private String[] axisNames;
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

	public void setAxisNames(String[] axisNames) {
		this.axisNames = axisNames;
	}
	
	public String[] getAxisNames() {
		return axisNames;
	}

	public void setSRSName(String srsName) {
		this.srsName = srsName;
	}

	public String getSRSName() {
		return srsName;
	}

	public void setOffsetVectors(ArrayList<double[]> offsetVectors) {
		this.offsetVectors = offsetVectors;
	}
	
	public ArrayList<double[]> getOffsetVectors() {
		return offsetVectors;
	}

}
