package uk.ac.glam.smartwps.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.geotools.geometry.jts.ReferencedEnvelope;

import uk.ac.glam.smartwps.shared.ows.BoundsSerializable;

/**
 * Some useful methods for the server side.
 * 
 * @author Jon Britton
 *
 */
public class ServerUtils {
	
	private static final Logger LOGGER = Logger.getLogger("smartwps.server");

	/**
	 * Create a BoundsSerializable object from a ReferencedEnvelope
	 * @param envelope
	 * @return
	 */
	public static BoundsSerializable boundsFromReferencedEnvelope(
			ReferencedEnvelope envelope) {
		BoundsSerializable bounds = new BoundsSerializable();
		bounds.setMinX(envelope.getMinX());
		bounds.setMaxX(envelope.getMaxX());
		bounds.setMinY(envelope.getMinY());
		bounds.setMaxY(envelope.getMaxY());
		bounds.setProjection(envelope.getCoordinateReferenceSystem().getName()
				.toString());
		return bounds;
	}

	/**
	 * Returns a String representation of a Thowable's stack trace.
	 * @param throwable
	 * @return
	 */
	public static String getStackTraceAsString(Throwable throwable) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		throwable.printStackTrace(printWriter);
		return writer.toString();
	}
	
	/**
	 * Convenience method for determining whether a String array contains a given String
	 * @param array array to search
	 * @param theString String to search for
	 * @return
	 */
	public static boolean arrayContainsString(String[] array, String theString) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(theString)) {
				LOGGER.info("Found string in array: " + array[i]);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Convenience method for changing the host in URL
	 * @param localURL localhost URL
	 * @param publicHost the name of the public host
	 * @return
	 * @throws MalformedURLException 
	 */
	public static String convertLocalToPublicURL(String localURL, String publicHost) throws MalformedURLException {
		LOGGER.info("Constructed new public URL from " + localURL + ", with new host " + publicHost);
		URL url = new URL(localURL);
		String newURL = url.getProtocol() + "://" + publicHost + ":" + 
				url.getPort() + url.getPath() + "?" + url.getQuery();
		return newURL;
	}
	
	public static int levenshteinDistance(String s, String t) {
		int n = s.length();
		; // length of s
		int m = t.length();
		; // length of t
		int[][] d = new int[n + 1][m + 1]; // matrix

		// Step 1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}

		// Step 2
		for (int i = 0; i <= n; i++) {
			d[i][0] = i;
		}
		for (int j = 0; j <= m; j++) {
			d[0][j] = j;
		}

		// Step 3
		for (int i = 1; i <= n; i++) {
			char s_i = s.charAt(i - 1); // ith character of s
			// Step 4
			for (int j = 1; j <= m; j++) {
				char t_j = t.charAt(j - 1); // jth character of t
				// Step 5
				int cost = (s_i == t_j) ? 0 : 1;
				// Step 6
				d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1),
						d[i - 1][j - 1] + cost);
			}
		}

		// Step 7
		return d[n][m];
	}
	
	public static File writeToFile(InputStream in, String filename) throws IOException {
		// write the inputStream to a FileOutputStream
		File file = new File(System.getProperty("java.io.tmpdir") + "/"
				+ filename);
		LOGGER.info("Creating file: " + file.getAbsolutePath());
		OutputStream out;
		out = new FileOutputStream(file);

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = in.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		in.close();
		out.flush();
		out.close();

		LOGGER.info("Created temp file: " + file.getAbsolutePath());

		return file;
	}
}
