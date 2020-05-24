package org.dice_research.opal.github;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * GitHub API requests to JSON.
 * 
 * Logs JSON if {@link Cfg#getPrintJson()} is set.
 * 
 * @author Adrian Wilke
 */
public class GithubApi {

	private static final Logger LOGGER = LogManager.getLogger();
	public static final String GITHUB_API_URL = "https://api.github.com";

	/**
	 * GET /orgs/:org/repos
	 * 
	 * @see https://developer.github.com/v3/repos/#list-organization-repositories
	 */
	public JSONArray listOrganizationRepositories(String org) {
		return requestApi("/orgs/" + org + "/repos");
	}

	/**
	 * Requests GitHub API.
	 * 
	 * @see https://developer.github.com/v3/
	 */
	public JSONArray requestApi(String request) {

		try {
			LOGGER.info(request);

			JSONArray jsonArray = (JSONArray) new JSONParser().parse(Utils.readJsonFromUrl(GITHUB_API_URL + request));

			if (Cfg.getPrintJson()) {
				LOGGER.info(System.lineSeparator() + Utils.format(jsonArray));
			}

			return jsonArray;
		} catch (ParseException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}