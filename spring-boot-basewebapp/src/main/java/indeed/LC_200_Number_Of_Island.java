package indeed;

/**
 * @author franksun
 * 
 *         Mar 17, 2020
 * 
 */
public class LC_200_Number_Of_Island {
	public int numIslands(char[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					dfs(grid, i, j);
					count++;
				}
			}
		}
		return count;
	}

	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, 1, 0, -1 };

	private void dfs(char[][] grid, int row, int col) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') {
			return;
		}
		grid[row][col] = '0';
		for (int i = 0; i < 4; i++) {
			int x = row + dx[i];
			int y = col + dy[i];
			dfs(grid, x, y);
		}
	}
}
