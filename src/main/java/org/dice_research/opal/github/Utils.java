package org.dice_research.opal.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import com.cedarsoftware.util.io.JsonWriter;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Utilities.
 * 
 * @author Adrian Wilke
 */
public abstract class Utils {

	/**
	 * Returns formatted lines of JSON array.
	 */
	public static String format(String jsonString) {
		return JsonWriter.formatJson(jsonString);
	}

	/**
	 * Increments counter in map.
	 */
	public static void increment(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			map.put(key, map.get(key) + 1);
		} else {
			map.put(key, 1);
		}
	}

	/**
	 * Reads JSON from URL.
	 * 
	 * @see https://stackoverflow.com/a/4308662
	 */
	public static String readJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			return sb.toString();
		} finally {
			is.close();
		}
	}

	/**
	 * Returns JSON array.
	 */
	public static JsonArray toJsonArray(String json) {
		return JsonParser.parseString(json).getAsJsonArray();
	}
}