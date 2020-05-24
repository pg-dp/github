package org.dice_research.opal.github;

import org.json.simple.JSONArray;

public class GitHub {

	GithubApi githubApi = new GithubApi();

	public JSONArray listOrganizationRepositories(String org) {
		JSONArray jsonArray = githubApi.listOrganizationRepositories(org);
//		jsonArray.stream().filter(y->y.toString()).collect();
//		jsonArray.forEach(System.out::println);

//		jsonArray.stream().filter(obj -> obj instanceof JSONArray).map(obj -> (JSONArray) obj)
//				.collect(Collectors.toList());
//
//		 Object o = jsonArray.stream().filter(JSONArray.class::isInstance).map(JSONArray.class::cast)
//				.collect(Collectors.toList());
//
//		Stream s = jsonArray.stream().map(JSONArray.class::cast);

		return jsonArray;
	}

}