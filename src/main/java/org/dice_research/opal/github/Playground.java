package org.dice_research.opal.github;

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

		// Test: Get repositories
		if (Boolean.TRUE) {
			System.out.println(gitHub.getOrganizationRepositories(Cfg.getGithubOwner()));
		}

	}

}