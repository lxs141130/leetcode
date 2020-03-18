package microsoft.OTAs;

/**
 * @author franksun
 * 
 *         Feb 11, 2020
 * 
 */
public class MS_Longest_SemiAlternating_Substring {

	private static int findLongestSemiAlteringSubstring(String str) {
		int len = 0;
		if (str == null) {
			return 0;
		}
		if (str.length() < 3) {
			return str.length();
		}
		for (int i = 2, j = 0; i < str.length(); ++i) {
			if (str.charAt(i) == str.charAt(i - 1) && str.charAt(i) == str.charAt(i - 2)) {
				j = i - 1;
			}
			len = Math.max(len, i - j + 1);
		}
		return len;
	}

	public static void main(String[] args) {
		String s1 = "baaabbabbb";
		String s2 = "babba";
		String s3 = "abaaaa";
		String s4 = "a";
		String s5 = "abbbaaabbbaaabbaa";


		System.out.println(findLongestSemiAlteringSubstring(s1));
		System.out.println(findLongestSemiAlteringSubstring(s2));
		System.out.println(findLongestSemiAlteringSubstring(s3));
		System.out.println(findLongestSemiAlteringSubstring(s4));
		System.out.println(findLongestSemiAlteringSubstring(s5));
	}
}
