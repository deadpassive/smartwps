package uk.ac.glam.smartwps.base.shared.utils;

/**
 * TODO: document
 * 
 * @author Jon Britton
 */
public class StringUtils {

    private StringUtils() {}
    
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
    
    /**
     * TODO: document
     * @param string1
     * @param string2
     * @return
     */
    public static boolean containsIgnoreCase(String string1, String string2) {
        return string1.toLowerCase().contains(string2.toLowerCase());
    }
}
