package org.dice_research.opal.github;

import org.json.simple.JSONArray;

/**
 * Manual API and code testing.
 * 
 * @author Adrian Wilke
 */
public class Playground {

	public static void main(String[] args) {
		String owner = Cfg.getGithubOwner();
		GithubApi githubApi = new GithubApi();
		GitHub gitHub = new GitHub();
		
		gitHub.listOrganizationRepositories(Cfg.getGithubOwner());
		
//		JSONArray jsonArray = githubApi.listOrganizationRepositories(owner);
//		System.out.println(Utils.format(jsonArray));
		
		
	}

}