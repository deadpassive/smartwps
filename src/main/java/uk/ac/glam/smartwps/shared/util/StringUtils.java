package uk.ac.glam.smartwps.shared.util;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class StringUtils {

	/**
	 * TODO: document
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(String string) {
		if (string == null) {
			return true;
		}
		return string.trim().isEmpty();
	}
}
