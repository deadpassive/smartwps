package uk.ac.glam.smartwps.shared.wcs100;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GridEnvelope implements Serializable {

	private int[] high;
	private int[] low;

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
	public void setHigh(int[] highs) {
		this.high = highs;
	}

    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
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
