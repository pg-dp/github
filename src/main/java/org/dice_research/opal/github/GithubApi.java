package org.dice_research.opal.github;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * GitHub API requests to JSON strings.
 * 
 * Logs JSON if {@link Cfg#getPrintJson()} is set.
 * 
 * @author Adrian Wilke
 */
public class GithubApi {

	private static final String GITHUB_API_URL = "https://api.github.com";
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * GET /orgs/:org/repos
	 * 
	 * @see https://developer.github.com/v3/repos/#list-organization-repositories
	 */
	public String listOrganizationRepositories(String org) {
		return requestApi("/orgs/" + org + "/repos");
	}

	/**
	 * GET /repos/:owner/:repo/pulls
	 * 
	 * @see https://developer.github.com/v3/pulls/#list-pull-requests
	 */
	public String listPullRequests(String owner, String repo) {
		return requestApi("/repos/" + owner + "/" + repo + "/pulls");
	}

	/**
	 * GET /repos/:owner/:repo/pulls/:pull_number/reviews
	 * 
	 * @see https://developer.github.com/v3/pulls/reviews/#list-reviews-on-a-pull-request
	 */
	public String listReviewsOnPullRequest(String owner, String repo, String pullNumber) {
		return requestApi("/repos/" + owner + "/" + repo + "/pulls/" + pullNumber + "/reviews");
	}

	/**
	 * Requests GitHub API.
	 * 
	 * @see https://developer.github.com/v3/
	 */
	public String requestApi(String request) {
		LOGGER.info(request);

		try {

			String json = Utils.readJsonFromUrl(GITHUB_API_URL + request);

			if (Cfg.getPrintJson()) {
				LOGGER.info("Response:" + System.lineSeparator() + Utils.format(json));
			}

			return json;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}