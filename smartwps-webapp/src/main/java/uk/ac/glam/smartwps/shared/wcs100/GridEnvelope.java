package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;

/**
 * @TODO: document
 * @author jonb
 *
 */
public class GridEnvelope implements Serializable {

	private int[] high;
	private int[] low;

    /**
     * TODO: document
     * @param highs
     */
    public void setHigh(int[] highs) {
        this.high = new int[highs.length];
        System.arraycopy(highs, 0, this.high, 0, highs.length);
	}

    /**
     * TODO: document
     * @param lows
     */
    public void setLow(int[] lows) {
        this.low = new int[lows.length];
        System.arraycopy(lows, 0, this.low, 0, lows.length);
	}

	/**
	 * TODO: document
	 * @return
	 */
	public int[] getHigh() {
		return high;
	}

	/**
	 * TODO: document
	 * @return
	 */
	public int[] getLow() {
		return low;
	}

}
