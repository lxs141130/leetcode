package microsoft.OTAs;

/**
 * @author franksun
 * 
 *         Feb 11, 2020
 * 
 */
// time -> O(N)
// space -> O(N)

public class MS_Lexicographically_Smallest_String {
	public String lexiSmallestString(String s) {
		//corner case, invalid input
		if (s == null || s.length() <= 1) {
			return "";
		}
		StringBuilder str = new StringBuilder(s);
		int i = 0;
		for (; i < s.length() - 1; i++) {
			if (s.charAt(i) > s.charAt(i + 1)) {
				break;
			}
		}
		//here if not break from the for loop, 'i' will be s.length() - 1
		// for test case with all character inside s already sorted
		return str.deleteCharAt(i).toString();
	}

	public static void main(String[] args) {
		MS_Lexicographically_Smallest_String ls = new MS_Lexicographically_Smallest_String();
		String s1 = "abcde";
		System.out.println(ls.lexiSmallestString(s1));

		String s2 = "edcba";
		System.out.println(ls.lexiSmallestString(s2));

		String s3 = "abced";
		System.out.println(ls.lexiSmallestString(s3));
	}
}
