package org.dice_research.opal.github;

import java.util.List;

/**
 * Manual API and code testing.
 * 
 * @author Adrian Wilke
 */
public class Playground {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// Classes
		GithubApi githubApi = new GithubApi();
		GitHub gitHub = new GitHub();

		// Values
		String owner = Cfg.getGithubOwner();
		String repo = "civet";
		String pullNumber = "30";

		// Test: Get repositories
		// e.g. [LauNuts, catfish, civet, common, github, KnowledgeBase]
		if (Boolean.FALSE) {
			System.out.println(gitHub.listOrganizationRepositories(owner));
		}

		// Test: Get pull requests
		// e.g. [33, 31, 30]
		if (Boolean.FALSE) {
			System.out.println(Utils.format(githubApi.listPullRequests(owner, repo)));
		}
		if (Boolean.FALSE) {
			System.out.println(gitHub.listPullRequests(owner, repo));
		}

		// Test: Get reviews on pull request
		if (Boolean.FALSE) {
			System.out.println(Utils.format(githubApi.listReviewsOnPullRequest(owner, repo, pullNumber)));
		}
		if (Boolean.FALSE) {
			List<Review> reviews = gitHub.listReviewsOnPullRequest(owner, repo, pullNumber);
			StringBuilder stringBuilder = new StringBuilder();
			for (Review review : reviews) {
				stringBuilder.append(review);
			}
			System.out.println(stringBuilder.toString());
		}

	}

}