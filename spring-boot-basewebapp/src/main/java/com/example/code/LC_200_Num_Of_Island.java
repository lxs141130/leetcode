package com.example.code;

public class LC_200_Num_Of_Island {
	public int[] dx = { 0, 1, 0, -1 };
	public int[] dy = { -1, 0, 1, 0 };

	public int numIslands(boolean[][] grid) {
		// write your code here
		int rst = 0;
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return rst;
		}
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == true) {
					helper(grid, i, j);
					rst++;
				}
			}
		}
		return rst;
	}

	private void helper(boolean[][] grid, int i, int j) {
		if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] == false) {
			return;
		}
		grid[i][j] = false;
		for (int k = 0; k <= 3; k++) {
			int row = i + dx[k];
			int col = j + dy[k];
			helper(grid, row, col);
		}

	}

	public static void main(String[] args) {
		LC_200_Num_Of_Island nos = new LC_200_Num_Of_Island();
		boolean[][] grid = { { Boolean.TRUE, Boolean.TRUE } };
		System.out.println(nos.numIslands(grid));
	}
}
