package microsoft.OTAs;

/**
 * @author franksun
 * 
 *         Feb 11, 2020
 * 
 */

//https://leetcode.com/discuss/interview-question/398056/

//time O(N)
//space O(1)
public class MS_Max_a_to_Insert {
	public static void main(String[] args) {
		String s1 = "aabab";
		String s2 = "dog";
		String s3 = "aa";
		String s4 = "baaa";
		String s5 = "aaba";
		System.out.println(maxA(s1));
		System.out.println(maxA(s2));
		System.out.println(maxA(s3));
		System.out.println(maxA(s4));
		System.out.println(maxA(s5));
	}

	private static int maxA(String s) {
		if (s.length() == 0) {
			return 2;
		}
		int countA = 0, countOther = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'a') {
				countA++;
			} else {
				countOther++;
				countA = 0;
			}
			if (countA == 3) {
				return -1;
			}
		}
		// pairs of A's as separators and bookends for the non-a characters
		//like s = abb, countOther will be 2, and we can insert 2 * 3 = 6 a
		//but we already has 1 a, which is represented by 3 - 2
		//total a needed to be inserted is 2 * 3 - 1 = 5 
		return 2 * (countOther + 1) - (s.length() - countOther);

	}

}
