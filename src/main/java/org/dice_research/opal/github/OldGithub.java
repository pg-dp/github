package org.dice_research.opal.github;

/**
 * GitHub API requests.
 * 
 * @see https://developer.github.com/v3/
 * 
 * @author Adrian Wilke
 */
public class OldGithub {

//	public static final List<String> REPOSITORIES = new LinkedList<>();
//	private GithubApi githubApi = new GithubApi();
//
//	static {
//		REPOSITORIES.add("LauNuts");
//	}
//
//	public static void main(String[] args) throws Exception {
//
//		System.out.println(Cfg.getGithubOwner());
//
//		List<String> repos;
//
//		if (REPOSITORIES != null && !REPOSITORIES.isEmpty()) {
//
//			repos = REPOSITORIES;
//
//		} else if (args.length >= 2) {
//			repos = new LinkedList<>(Arrays.asList(args));
//			repos.remove(0);
//
//		} else {
//			System.out.println("Please provide owner and at least one repo");
//			return;
//		}
//
//		for (String repo : repos) {
//			Map<String, Integer> accountToComments = new OldGithub().countComments(Cfg.getGithubOwner(), repo);
//			int sum = 0;
//			for (Integer comments : accountToComments.values()) {
//				sum += comments;
//			}
//			System.out.println(repo + " " + accountToComments + " sum: " + sum);
//		}
//	}
//
//	/**
//	 * API: GET /repos/:owner/:repo/pulls/comments
//	 * 
//	 * E.g. https://api.github.com/repos/pg-dp/civet/pulls/comments
//	 * 
//	 * @see https://developer.github.com/v3/pulls/comments/#list-comments-in-a-repository
//	 */
//	protected Map<String, Integer> countComments(String owner, String repo) throws Exception {
//
//		JSONArray data = githubApi.requestApi("/repos/" + owner + "/" + repo + "/pulls/comments");
//
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		for (int i = 0; i < data.length(); i++) {
//			JSONObject jo = data.getJSONObject(i);
//			if (jo.has("user")) {
//				JSONObject joUser = jo.getJSONObject("user");
//
//				if (joUser.has("login")) {
//					String login = joUser.getString("login");
//					Utils.increment(map, login);
//
//				} else {
//					System.err.println("No login " + jo.get("id"));
//				}
//			} else {
//				System.err.println("No user " + jo.get("id"));
//			}
//		}
//
//		return map;
//	}
}