package uk.ac.glam.smartwps.shared.ows;

import java.io.Serializable;

import org.gwtopenmaps.openlayers.client.Bounds;

/**
 * A simple class for storing a bounding box in a given projection.
 * 
 * @author Jon Britton
 *
 */
public class BoundsSerializable implements Serializable {

	private static final long serialVersionUID = -2610165953491695869L;
	double minX;
	double minY;
	double maxX;
	double maxY;
	String projection;
	
	public BoundsSerializable(){}
	public BoundsSerializable(double minX, double minY, double maxX,
			double maxY, String projection) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		this.projection = projection;
	}
	public BoundsSerializable(Bounds bounds, String requestCRS) {
		this.minX = bounds.getLowerLeftX();
		this.minY = bounds.getLowerLeftY();
		this.maxX = bounds.getUpperRightX();
		this.maxY = bounds.getUpperRightY();
		this.projection = requestCRS;
	}
	public String getProjection() {
		return projection;
	}
	public void setProjection(String projection) {
		this.projection = projection;
	}
	public double getMinX() {
		return minX;
	}
	public void setMinX(double minX) {
		this.minX = minX;
	}
	public double getMinY() {
		return minY;
	}
	public void setMinY(double minY) {
		this.minY = minY;
	}
	public double getMaxX() {
		return maxX;
	}
	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}
	public double getMaxY() {
		return maxY;
	}
	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}
	
	public String toString() {
		return "bounds: (" + minX + "," + minY + "),(" + maxX + "," + maxY + "), proj: " + projection;
	}
	
	public Bounds getBounds() {
		return new Bounds(minX, minY, maxX, maxY);
	}
	
	/**
	 * Returns a string formatted for WCS 1.1.1 GetCoverage requests
	 * @return
	 */
	public String getWCS111FormattedString() {
		return minX + "," + minY + "," + maxX + "," + maxY + "," + projection;
	}
	
}
