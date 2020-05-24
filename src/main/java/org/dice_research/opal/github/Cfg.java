package org.dice_research.opal.github;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Simple file and properties based configuration.
 * 
 * @author Adrian Wilke
 */
public abstract class Cfg {

	public static final String KEY_OWNER = "owner";
	public static final String KEY_PRINT_JSON = "print.json";
	public static final String FILE = "config.properties";

	public static String getGithubOwner() {
		return get(KEY_OWNER);
	}

	public static boolean getPrintJson() {
		return Boolean.parseBoolean(get(KEY_PRINT_JSON));
	}

	private static String get(String key) {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(new File(FILE)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return properties.get(key).toString();
	}
}