package indeed;

/**
 * @author franksun
 * 
 *         Mar 17, 2020
 * 
 */
public class LC_221_Maximal_Square {
	// This problem can be solved by dynamic programming.
	// The changing condition is:
	// t[i][j] = min(t[i][j-1], t[i-1][j], t[i-1][j-1]) + 1.
	// It means the square formed before this point.
	public int maximalSquare(char[][] matrix) {
		int maxsqlen = 0;//res is the side length
		if (matrix == null || matrix.length == 0) {
			return maxsqlen;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] dp = new int[row + 1][col + 1];

		//traversal whole matrix
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					maxsqlen = Math.max(maxsqlen, dp[i][j]);
				}
			}
		}
		return maxsqlen * maxsqlen;//area = length * length
	}
}
