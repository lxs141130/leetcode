package com.example.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC_139_Word_Break {
	public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) {
			return true;
		}
		if (wordDict == null || wordDict.size() == 0) {
			return false;
		}
		List<List<String>> rst = new ArrayList<>();
		helper(s, wordDict, rst, new ArrayList<String>(), 0);
		return rst.size() != 0;
	}

	private void helper(String s, List<String> wordDict, List<List<String>> rst, List<String> temp, int start) {
		if (start == s.length()) {
			rst.add(new ArrayList<String>(temp));
			return;
		}
		for (int j = start + 1; j <= s.length(); j++) {
			if (wordDict.contains(s.substring(start, j))) {
				temp.add(s.substring(start, j));
				helper(s, wordDict, rst, temp, j);
				temp.remove(temp.size() - 1);
			}
		}
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
