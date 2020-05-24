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

	public static final String FILE = "config.properties";

	public static final String KEY_OWNER = "owner";
	public static final String KEY_PRINT_JSON = "print.json";

	private static String get(String key) {
		File file = new File(FILE);
		if (!file.canRead()) {
			throw new RuntimeException("Could not read configuration file: " + file.getAbsolutePath());
		}

		Properties properties = new Properties();
		try {
			properties.load(new FileReader(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Object value = properties.get(key);
		if (value == null) {
			throw new RuntimeException("Could not find configuration key: " + key);
		} else {
			return value.toString();
		}
	}

	public static String getGithubOwner() {
		return get(KEY_OWNER);
	}

	public static boolean getPrintJson() {
		return Boolean.parseBoolean(get(KEY_PRINT_JSON));
	}
}