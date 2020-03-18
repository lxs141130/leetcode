package microsoft.OTAs;

/**
 * @author franksun
 * 
 *         Feb 11, 2020
 * 
 */
//time complexity -> O(N) N is the length of String A
//space complexity -> O(1), no extra space used

//https://leetcode.com/discuss/interview-question/398026/
public class MS_Min_Moves {
	public int minMoves(String s) {
		//corner case
		if (s == null || s.length() < 3) {
			return 0;
		}
		int swaps = 0;
		int len = s.length();
		for (int startIndex = 0; startIndex < len - 2; startIndex++) {
			//if three consecutive char existed, one swap needed, also need to move the startIndex to startIndex + 2
			if ((s.charAt(startIndex) == s.charAt(startIndex + 1)) && (s.charAt(startIndex + 1) == s.charAt(startIndex + 2))) {
				swaps++;
				startIndex += 2;
			}
		}
		return swaps;
	}

	public static void main(String[] args) {
		MS_Min_Moves mm = new MS_Min_Moves();
		String A1 = "aaabaaaaa";
		System.out.println(mm.minMoves(A1));

		String A2 = "baaabbaabbba";
		System.out.println(mm.minMoves(A2));

		String A3 = "baabab";
		System.out.println(mm.minMoves(A3));

		String A4 = "bbaaaaaabbab";
		System.out.println(mm.minMoves(A4));

		String A5 = "abbbaaabbbaaabbaa";
		System.out.println(mm.minMoves(A5));
	}
}
