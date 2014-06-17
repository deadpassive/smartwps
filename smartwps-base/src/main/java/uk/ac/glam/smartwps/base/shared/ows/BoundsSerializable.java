package uk.ac.glam.smartwps.base.shared.ows;

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
	 * @return the minimum X value
	 */
	public double getMinX() {
		return minX;
	}
	
	/**
	 * Set the minimum X value
	 * @param minX the minimum X value
	 */
	public void setMinX(double minX) {
		this.minX = minX;
	}
	
	/**
	 * @return the minimum Y value
	 */
	public double getMinY() {
		return minY;
	}
	
	/**
	 * Set the minimum Y value.
	 * @param minY the minimum Y value
	 */
	public void setMinY(double minY) {
		this.minY = minY;
	}
	
	/**
	 * @return the maxmimum X value
	 */
	public double getMaxX() {
		return maxX;
	}
	
	/**
	 * Set the maximum X value
	 * @param maxX the maximum X value
	 */
	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}
	
	/**
	 * @return the maximum Y value
	 */
	public double getMaxY() {
		return maxY;
	}
	
	/**
	 * Set the maximum Y value
	 * @param maxY the maximum Y value
	 */
	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}
	
    @Override
	public String toString() {
		return "bounds: (" + minX + "," + minY + "),(" + maxX + "," + maxY + "), proj: " + projection;
	}
	
	/**
	 * Return the OpenLayers equivalent of this bounds.
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
