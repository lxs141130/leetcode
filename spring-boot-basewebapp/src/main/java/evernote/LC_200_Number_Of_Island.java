package evernote;

/**
 * @author franksun
 * 
 *         Mar 9, 2020
 * 
 */
public class LC_200_Number_Of_Island {
	public int numIslands(char[][] grid) {
		int count = 0;
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return count;
		}
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
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

	private void dfs(char[][] board, int i, int j) {
		if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] == '0') {
			return;
		}
		board[i][j] = '0';
		for (int index = 0; index < 4; index++) {
			int x = i + dx[index];
			int y = j + dy[index];
			dfs(board, x, y);
		}
	}
}
