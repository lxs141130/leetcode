package microsoft.Onsite;

import java.util.Arrays;

/**
 * @author franksun
 * 
 *         Feb 26, 2020
 * 
 */
public class LC_1329_Sort_the_Matrix_diagonally {
	public int[][] diagonalSort(int[][] mat) {
		if (mat == null || mat.length <= 1) {
			return mat;
		}
		int m = mat.length;// 2
		int n = mat[0].length;// 3

		for (int r = m - 2, c = 0; r >= 0; r--) {
			int len = Math.min(m - r, n - c);
			int[] temp = new int[len];
			for (int i = 0; r + i < m && c + i < n; i++) {
				temp[i] = mat[r + i][c + i];
			}
			Arrays.sort(temp);
			for (int i = 0; r + i < m && c + i < n; i++) {
				mat[r + i][c + i] = temp[i];
			}
		}

		for (int r = 0, c = 1; c < n - 1; c++) {
			int len = Math.min(m - r, n - c);
			int[] temp = new int[len];
			for (int i = 0; r + i < m && c + i < n; i++) {
				temp[i] = mat[r + i][c + i];
			}
			Arrays.sort(temp);
			for (int i = 0; r + i < m && c + i < n; i++) {
				mat[r + i][c + i] = temp[i];
			}
		}
		return mat;
	}
}
