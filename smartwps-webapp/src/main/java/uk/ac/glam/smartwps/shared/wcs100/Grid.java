package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class Grid implements Serializable {

	private int dimension;
	private GridEnvelope limits;
	private List<String> axisNames;
	private String srsName;
	private ArrayList<double[]> offsetVectors;

	/**
	 * TODO: document
	 * @param dimension
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public int getDimension() {
		return dimension;
	}

	/**
	 * TODO: document
	 * @param limits
	 */
	public void setLimits(GridEnvelope limits) {
		this.limits = limits;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public GridEnvelope getLimits() {
		return limits;
	}

	/**
	 * TODO: document
	 * @param axisNames
	 */
	public void setAxisNames(List<String> axisNames) {
		this.axisNames = new ArrayList<String>(axisNames);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public ArrayList<String> getAxisNames() {
		return new ArrayList<String>(axisNames);
	}

	/**
	 * TODO: document
	 * @param srsName
	 */
	public void setSRSName(String srsName) {
		this.srsName = srsName;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public String getSRSName() {
		return srsName;
	}

	/**
	 * TODO: document
	 * @param offsetVectors
	 */
	public void setOffsetVectors(List<double[]> offsetVectors) {
		this.offsetVectors = new ArrayList<double[]>(offsetVectors);
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public List<double[]> getOffsetVectors() {
		return new ArrayList<double[]>(offsetVectors);
	}

}
