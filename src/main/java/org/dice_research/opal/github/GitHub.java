package org.dice_research.opal.github;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Parses GitHub API responses.
 * 
 * @author Adrian Wilke
 */
public class GitHub {

	private GithubApi githubApi = new GithubApi();

	public List<String> getOrganizationRepositories(String org) {
		String json = githubApi.listOrganizationRepositories(org);
		return StreamSupport.stream(Utils.toJsonArray(json).spliterator(), false)
				.map(o -> o.getAsJsonObject().get("name").getAsString()).collect(Collectors.toList());
	}

}