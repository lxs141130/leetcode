package com.example.code;

import java.util.HashMap;
import java.util.Map;

public class LC_76_MInimum_Window_Substring {
	public String minWindow(String source, String target) {
		// write your code here
		if (source == null || target == null) {
			return "";
		}
		if (source.length() < target.length()) {
			return "";
		}

		Map<Character, Integer> dictT = new HashMap<Character, Integer>();
		for (int i = 0; i < target.length(); i++) {
			int count = dictT.getOrDefault(target.charAt(i), 0);
			dictT.put(target.charAt(i), count + 1);
		}

		int required = dictT.size();
		int formed = 0;

		Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

		// ans list of the form (window length, left, right)
		int[] ans = { -1, 0, 0 };
		int left = 0;
		int right = 0;

		while (right < source.length()) {
			char c = source.charAt(right);
			int count = windowCounts.getOrDefault(c, 0);
			windowCounts.put(c, count + 1);

			if (dictT.containsKey(c) && dictT.get(c).intValue() == windowCounts.get(c).intValue()) {
				formed++;
			}

			// narrow down the window(move left to right until the window is not valid, we
			// get out of loop)
			// for s = aaaaaabc, t = abc
			// first valid window will be s
			// then move left to last a, will be the final answer
			while (left <= right && formed == required) {
				c = source.charAt(left);
				if (ans[0] == -1 || right - left + 1 < ans[0]) {
					ans[0] = right - left + 1;
					ans[1] = left;
					ans[2] = right;
				}

				windowCounts.put(c, windowCounts.get(c) - 1);
				if (dictT.containsKey(c) && dictT.get(c).intValue() > windowCounts.get(c).intValue()) {
					formed--;
				}

				left++;
			}
			right++;
		}
		return ans[0] == -1 ? "" : source.substring(ans[1], ans[2] + 1);
	}
	
	public static void main(String[] args) {
		LC_76_MInimum_Window_Substring mws = new LC_76_MInimum_Window_Substring();
		String source = "aaaaaaaafbc";
		String target = "ac";
		System.out.print(mws.minWindow(source, target));
	}
}
