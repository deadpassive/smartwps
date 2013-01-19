package uk.ac.glam.smartwps.data.shared.ows;

import java.io.Serializable;

import org.gwtopenmaps.openlayers.client.Bounds;

/**
 * A simple class for storing a bounding box in a given projection.
 * 
 * @author Jon Britton
 */
@SuppressWarnings("serial")
public class BoundsSerializable implements Serializable {

	private double minX;
	private double minY;
	private double maxX;
	private double maxY;
	private String projection;
	
	/**
	 * Empty constructor for serialization.
	 */
	public BoundsSerializable(){}
	
	/**
	 * TODO: document
	 * 
	 * @param minX
	 * @param minY
	 * @param maxX
	 * @param maxY
	 * @param projection
	 */
	public BoundsSerializable(double minX, double minY, double maxX, double maxY, String projection) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		this.projection = projection;
	}
	
	/**
	 * TODO: document
	 * @param bounds
	 * @param requestCRS
	 */
	public BoundsSerializable(Bounds bounds, String requestCRS) {
		this.minX = bounds.getLowerLeftX();
		this.minY = bounds.getLowerLeftY();
		this.maxX = bounds.getUpperRightX();
		this.maxY = bounds.getUpperRightY();
		this.projection = requestCRS;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public String getProjection() {
		return projection;
	}
	
	/**
	 * TODO: document
	 * @param projection
	 */
	public void setProjection(String projection) {
		this.projection = projection;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public double getMinX() {
		return minX;
	}
	
	/**
	 * TODO: document
	 * @param minX
	 */
	public void setMinX(double minX) {
		this.minX = minX;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public double getMinY() {
		return minY;
	}
	
	/**
	 * TODO: document
	 * @param minY
	 */
	public void setMinY(double minY) {
		this.minY = minY;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public double getMaxX() {
		return maxX;
	}
	
	/**
	 * TODO: document
	 * @param maxX
	 */
	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}
	
	/**
	 * TODO: document
	 * @return
	 */
	public double getMaxY() {
		return maxY;
	}
	
	/**
	 * TODO: document
	 * @param maxY
	 */
	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}
	
    @Override
	public String toString() {
		return "bounds: (" + minX + "," + minY + "),(" + maxX + "," + maxY + "), proj: " + projection;
	}
	
	/**
	 * TODO: document
	 * @return new OpenLayers Bounds object
	 */
	public Bounds getAsOLBounds() {
		return new Bounds(minX, minY, maxX, maxY);
	}
	
	/**
	 * @return a string formatted for WCS 1.1.1 GetCoverage requests
	 */
	public String getWCS111FormattedString() {
		return minX + "," + minY + "," + maxX + "," + maxY + "," + projection;
	}
	
}
