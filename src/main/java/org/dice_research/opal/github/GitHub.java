package org.dice_research.opal.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Gets comments in GitHub pull requests.
 * 
 * @author Adrian Wilke
 */
public class GitHub {

	public static final String GITHUB_API_URL = "https://api.github.com";

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.out.println("Please provide owner and at least one repo");
			return;
		}

		String owner = args[0];
		System.out.println(owner);
		for (int i = 1; i < args.length; i++) {
			System.out.println(args[i] + " " + new GitHub().getRepoComments(owner, args[i]));
		}
	}

	/**
	 * API: GET /repos/:owner/:repo/pulls/comments
	 * 
	 * E.g. https://api.github.com/repos/pg-dp/civet/pulls/comments
	 * 
	 * @return
	 * 
	 * @see https://developer.github.com/v3/pulls/comments/#list-comments-in-a-repository
	 */
	protected Map<String, Integer> getRepoComments(String owner, String repo) throws Exception {
		String url = GITHUB_API_URL + "/repos/" + owner + "/" + repo + "/pulls/comments";
		JSONArray data = new JSONArray(readJsonFromUrl(url));

		// Dev: Print data
		if (Boolean.FALSE)
			System.out.println(data.toString(2));

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = data.getJSONObject(i);
			if (jo.has("user")) {
				JSONObject joUser = jo.getJSONObject("user");

				if (joUser.has("login")) {
					String login = joUser.getString("login");
					increment(map, login);

				} else {
					System.err.println("No login " + jo.get("id"));
				}
			} else {
				System.err.println("No user " + jo.get("id"));
			}
		}

		return map;
	}

	protected void increment(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			map.put(key, map.get(key) + 1);
		} else {
			map.put(key, 1);
		}
	}

	/**
	 * @see https://stackoverflow.com/a/4308662
	 */
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/**
	 * @see https://stackoverflow.com/a/4308662
	 */
	private static String readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			return readAll(rd);
		} finally {
			is.close();
		}
	}

}
