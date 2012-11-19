package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;

public class GridEnvelope implements Serializable {

	private int[] high;
	private int[] low;

    public void setHigh(int[] highs) {
        this.high = new int[highs.length];
        System.arraycopy(highs, 0, this.high, 0, highs.length);
	}

    public void setLow(int[] lows) {
        this.low = new int[lows.length];
        System.arraycopy(lows, 0, this.low, 0, lows.length);
	}

	public int[] getHigh() {
		return high;
	}

	public int[] getLow() {
		return low;
	}

}
