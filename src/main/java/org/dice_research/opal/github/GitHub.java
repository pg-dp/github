package org.dice_research.opal.github;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.gson.JsonObject;

/**
 * Parses GitHub API responses.
 * 
 * @author Adrian Wilke
 */
public class GitHub {

	private GithubApi githubApi = new GithubApi();

	public List<String> listOrganizationRepositories(String org) {
		String json = githubApi.listOrganizationRepositories(org);
		return StreamSupport.stream(Utils.toJsonArray(json).spliterator(), false)
				.map(o -> o.getAsJsonObject().get("name").getAsString()).collect(Collectors.toList());
	}

	public List<String> listPullRequests(String owner, String repo) {
		String json = githubApi.listPullRequests(owner, repo);
		return StreamSupport.stream(Utils.toJsonArray(json).spliterator(), false)
				.map(o -> o.getAsJsonObject().get("number").getAsString()).collect(Collectors.toList());
	}

	public List<Review> listReviewsOnPullRequest(String owner, String repo, String pullNumber) {
		String json = githubApi.listReviewsOnPullRequest(owner, repo, pullNumber);
		List<JsonObject> reviewObjects = StreamSupport.stream(Utils.toJsonArray(json).spliterator(), false)
				.map(o -> o.getAsJsonObject()).collect(Collectors.toList());

		List<Review> reviews = new ArrayList<>(reviewObjects.size());
		for (JsonObject jsonObject : reviewObjects) {
			Review review = new Review();
			review.userLogin = jsonObject.get("user").getAsJsonObject().get("login").getAsString();
			review.htmlUrl = jsonObject.get("html_url").getAsString();
			review.submittedAt = jsonObject.get("submitted_at").getAsString();
			reviews.add(review);
		}
		return reviews;
	}

}