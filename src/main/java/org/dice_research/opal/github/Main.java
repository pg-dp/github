package org.dice_research.opal.github;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();

		// Print configuration
		System.out.println(main.getCfgInfo());

		StringBuilder stringBuilder = new StringBuilder();

		// Get data
		List<Review> reviews = main.getReviews();
		for (Review review : reviews) {
			stringBuilder.append(review);
		}

		// Sum up
		Map<String, Integer> reviewCountMap = main.countReviews(reviews);
		for (Entry<String, Integer> entry : reviewCountMap.entrySet()) {
			stringBuilder.append(entry.getKey() + " " + entry.getValue());
			stringBuilder.append(System.lineSeparator());
		}

		System.out.println(stringBuilder);
	}

	private Map<String, Integer> countReviews(List<Review> reviews) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Review review : reviews) {
			Utils.increment(map, review.userLogin);
		}
		return map;
	}

	private GitHub gitHub = new GitHub();

	private StringBuilder getCfgInfo() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Configuration:       ");
		stringBuilder.append(System.lineSeparator());

		stringBuilder.append("File:                ");
		stringBuilder.append(Cfg.getFile().getAbsolutePath());
		stringBuilder.append(System.lineSeparator());

		stringBuilder.append("Print JSON:          ");
		stringBuilder.append(Cfg.getPrintJson());
		stringBuilder.append(System.lineSeparator());

		stringBuilder.append("Github owner:        ");
		stringBuilder.append(Cfg.getGithubOwner());
		stringBuilder.append(System.lineSeparator());

		stringBuilder.append("Github repositories: ");
		stringBuilder.append(Cfg.getRepositories());
		stringBuilder.append(System.lineSeparator());

		return stringBuilder;
	}

	private List<Review> getReviews() {
		List<Review> reviews = new LinkedList<>();
		for (String repo : Cfg.getRepositories()) {
			for (String pullRequest : gitHub.listPullRequests(Cfg.getGithubOwner(), repo)) {
				reviews.addAll(gitHub.listReviewsOnPullRequest(Cfg.getGithubOwner(), repo, pullRequest));
			}
		}
		return reviews;
	}
}