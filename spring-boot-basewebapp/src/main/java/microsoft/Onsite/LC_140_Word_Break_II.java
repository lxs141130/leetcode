package microsoft.Onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author franksun
 * 
 *         Mar 5, 2020
 * 
 */
public class LC_140_Word_Break_II {
	public List<String> wordBreak(String s, List<String> wordDict) {
		List<String> res = new ArrayList<>();
		Map<String, List<String>> memo = new HashMap<>();
		if (s == null || s.isEmpty()) {
			return res;
		}
		return dfs(s, wordDict, memo);
	}

	public List<String> dfs(String s, List<String> words, Map<String, List<String>> memo) {
		// already has the mapping for after substring
		//we can just return instead of do dfs again
		if (memo.containsKey(s)) {
			return memo.get(s);
		}
		List<String> res = new ArrayList<>();

		// hit the end of s
		if (s.isEmpty()) {
			res.add("");
			return res;
		}

		for (String w : words) {
			if (s.startsWith(w)) {
				List<String> sub = dfs(s.substring(w.length()), words, memo);
				for (String ss : sub) {
					// hit the end, w is the last word
					if (ss.isEmpty()) {
						res.add(w);
					} else {
						res.add(w + " " + ss);
					}
				}
			}
		}
		// populate memo map
		memo.put(s, res);
		return res;
	}

	public static void main(String[] args) {
		LC_140_Word_Break_II wb = new LC_140_Word_Break_II();
//		String s1 = "lintcode";
//		List<String> wordDict1 = Arrays.asList("lint", "code");
//		System.out.println(wb.wordBreak(s1, wordDict1));

		String s2 = "catsanddog";
		List<String> wordDict2 = Arrays.asList("cats", "dog", "sand", "and", "cat");
		System.out.println(wb.wordBreak(s2, wordDict2));
	}
}
