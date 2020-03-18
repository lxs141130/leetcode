package com.example.code;

import java.util.HashMap;
import java.util.Map;

public class LC_3_Longest_Substr_Without_Repeating {
	public int lengthOfLongestSubstring(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) != null) {
                int index = map.get(c);
                start = Math.max(index, start);
            } 
            maxLen = Math.max(maxLen, i - start + 1);
            map.put(c, i + 1);
        }
        return maxLen;
    }
	
	public static void main(String[] args) {
		LC_3_Longest_Substr_Without_Repeating ls = new LC_3_Longest_Substr_Without_Repeating();
		String s = "an++--jayv";
		System.out.println(ls.lengthOfLongestSubstring(s));
		
		int[] arr = {1,1,2,2,3,3,4,4,5};
		int rst = arr[0];
		for (int i = 1; i < arr.length; i++) {
			rst ^= arr[i];
		}
		System.out.println(rst);
	}
}
