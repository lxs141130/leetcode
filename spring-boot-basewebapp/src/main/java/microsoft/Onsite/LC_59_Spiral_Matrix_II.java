package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Feb 26, 2020
 * 
 */
public class LC_59_Spiral_Matrix_II {
	public int[][] generateMatrix(int n) {
		if (n == 1) {
			return new int[][] {{1}};
		}
		int[][] matrix = new int[n][n];
		int value = 1;
		int r1 = 0, r2 = n - 1;
		int c1 = 0, c2 = n - 1;
		while (r1 <= r2 && c1 <= c2) {
			//1,2,3
			for (int c = c1; c <= c2; c++) {
				matrix[r1][c] = value++;
			}
			//4,5
			for (int r = r1 + 1; r <= r2; r++) {
				matrix[r][c2] = value++;
			}
			if(r1 < r2 && c1 < c2) {
				//6
				for (int c = c2 - 1; c > c1; c--) {
					matrix[r2][c] = value++;
				}
				//7,8
				for (int r = r2; r > r1; r--) {
					matrix[r][c1] = value++;
				}
			}
			r1++;
			r2--;
			c1++;
			c2--;
		}
		return matrix;
	}
}
