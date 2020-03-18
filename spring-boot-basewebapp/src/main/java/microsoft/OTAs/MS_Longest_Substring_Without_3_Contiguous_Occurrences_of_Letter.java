package microsoft.OTAs;

/**
 * @author franksun
 * 
 * Feb 11, 2020
 * 
 */
//time -> O(N)
//space -> O(1)
public class MS_Longest_Substring_Without_3_Contiguous_Occurrences_of_Letter {
	
	public String validLongestSubstring(String s) {
		if (s == null || s.length() < 3) {
			return s;
		}
		int curStart = 0, curEnd = 1;
		char c = s.charAt(0);
		int count = 1;
		int maxLen = 1;
		int maxStart = 0;
		while (curEnd < s.length()) {
			if (s.charAt(curEnd) == c) {
				count++;
				if (count == 2) {
					//we only update maxStart to curStart if the length is larger than maxLen
					if (curEnd - curStart + 1 > maxLen) {
						maxLen = curEnd - curStart + 1;
						maxStart = curStart;
					}
				} else {
					//we update curStart when there is 3 consecutive identical char existed
					curStart = curEnd - 1;
				}
			} else {
				//update char to check to curEnd to check consecutive length of same char
				c = s.charAt(curEnd);
				//anytime the char is not match, we update count to 1
				count = 1;
				//we only update maxStart to curStart if the length is larger than maxLen
				if (curEnd - curStart + 1 > maxLen) {
					maxLen = curEnd - curStart + 1;
					maxStart = curStart;
				}
			}
			curEnd++;
		}
		return s.substring(maxStart, maxStart + maxLen);
	}
	
	public static void main(String[] args) {
		MS_Longest_Substring_Without_3_Contiguous_Occurrences_of_Letter ls = new MS_Longest_Substring_Without_3_Contiguous_Occurrences_of_Letter();
		String s1 = "aabbaaaaabb";
		System.out.println(ls.validLongestSubstring(s1));
		
		String s2 = "aabbaabbaabbaa";
		System.out.println(ls.validLongestSubstring(s2));
		
		String s3 = "aaabaaa";
		System.out.println(ls.validLongestSubstring(s3));
		
		String s4 = "abbaabbaaabbaaa";
		System.out.println(ls.validLongestSubstring(s4));
		
		String s5 = "aaaabbaabbaaabbaaa";
		System.out.println(ls.validLongestSubstring(s5));
		
		String s6 = "abbbaaabbbaaabbaa";
		System.out.println(ls.validLongestSubstring(s6));
	}
}
