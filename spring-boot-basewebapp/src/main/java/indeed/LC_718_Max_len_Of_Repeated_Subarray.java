package indeed;

/**
 * @author franksun
 * 
 *         Feb 7, 2020
 * 
 */
public class LC_718_Max_len_Of_Repeated_Subarray {
	
	public int findLength(int[] A, int[] B) {
		int ans = 0;
		int[][] dp = new int[A.length + 1][B.length + 1];
		for (int i = A.length - 1; i >= 0; i--) {
			for (int j = B.length - 1; j >= 0; j-- ) {
				if (A[i] == B[j]) {
					dp[i][j] = dp[i + 1][j + 1] + 1;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		LC_718_Max_len_Of_Repeated_Subarray ml = new LC_718_Max_len_Of_Repeated_Subarray();
		int[] A = { 1, 2, 4, 3, 2, 1 };
		int[] B = { 3, 2, 1, 3 };
		System.out.println(ml.findLength(A, B));

	}
}