package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;

public class GridEnvelope implements Serializable {

	private static final long serialVersionUID = 4397495502118078744L;
	private int[] high;
	private int[] low;

	public void setHigh(int[] highs) {
		this.high = highs;
	}

	public void setLow(int[] lows) {
		this.low = lows;
	}

	public int[] getHigh() {
		return high;
	}

	public int[] getLow() {
		return low;
	}

}
