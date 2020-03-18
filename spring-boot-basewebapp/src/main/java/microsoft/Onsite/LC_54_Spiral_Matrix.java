package microsoft.Onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franksun
 * 
 *         Feb 26, 2020
 * 
 */
public class LC_54_Spiral_Matrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		if (matrix == null || matrix.length == 0) {
			return res;
		}
		int r1 = 0, r2 = matrix.length - 1;
		int c1 = 0, c2 = matrix[0].length - 1;
		while (r1 <= r2 && c1 <= c2) {
			// left to right
			for (int j = c1; j <= c2; j++) {
				res.add(matrix[r1][j]);
			}
			// top to bottom
			for (int j = r1 + 1; j <= r2; j++) {
				res.add(matrix[j][c2]);
			}
			if (r1 < r2 && c1 < c2) {
				// right to left
				for (int j = c2 - 1; j > c1; j--) {
					res.add(matrix[r2][j]);
				}
				// bottom to top
				for (int j = r2; j > r1; j--) {
					res.add(matrix[j][c1]);
				}
			}
			r1++;
			r2--;
			c1++;
			c2--;
		}
		return res;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		LC_54_Spiral_Matrix sm = new LC_54_Spiral_Matrix();

		System.out.println(sm.spiralOrder(matrix));
	}
}
