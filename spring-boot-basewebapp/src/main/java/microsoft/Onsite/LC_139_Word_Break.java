package microsoft.Onsite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_139_Word_Break {	
	public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) {
			return true;
		}
		if (wordDict == null || wordDict.size() == 0) {
			return false;
		}
		Set<String> wordDictSet = new HashSet<>(wordDict);
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}

	public static void main(String[] args) {
		LC_139_Word_Break wb = new LC_139_Word_Break();
		String s1 = "lintcode";
		String s2 = "catsandog";
		List<String> wordDict1 = Arrays.asList("lint", "code");
		List<String> wordDict2 = Arrays.asList("cats", "dog", "sand", "and", "cat");
		String s3 = "applepenapple";
		List<String> wordDict3 = Arrays.asList("apple", "pen");
		// ["cats", "dog", "sand", "and", "cat"]
    	System.out.println(wb.wordBreak(s1, wordDict1));
		System.out.println(wb.wordBreak(s2, wordDict2));
		System.out.println(wb.wordBreak(s3, wordDict3));

	}
}
