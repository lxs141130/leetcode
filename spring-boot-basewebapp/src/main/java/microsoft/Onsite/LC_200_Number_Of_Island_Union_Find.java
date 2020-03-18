package microsoft.Onsite;

public class LC_200_Number_Of_Island_Union_Find {

	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int nr = grid.length;
		int nc = grid[0].length;
		UnionFind uf = new UnionFind(grid);
		for (int r = 0; r < nr; ++r) {
			for (int c = 0; c < nc; ++c) {
				if (grid[r][c] == '1') {
					grid[r][c] = '0';
					//up
					if (r - 1 >= 0 && grid[r - 1][c] == '1') {
						uf.union(r * nc + c, (r - 1) * nc + c);
					}
					//down
					if (r + 1 < nr && grid[r + 1][c] == '1') {
						uf.union(r * nc + c, (r + 1) * nc + c);
					}
					//left
					if (c - 1 >= 0 && grid[r][c - 1] == '1') {
						uf.union(r * nc + c, r * nc + c - 1);
					}
					//right
					if (c + 1 < nc && grid[r][c + 1] == '1') {
						uf.union(r * nc + c, r * nc + c + 1);
					}
				}
			}
		}

		return uf.getCount();
	}

	public static void main(String[] args) {
		LC_200_Number_Of_Island_Union_Find nos = new LC_200_Number_Of_Island_Union_Find();
		char[][] grid = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };
		System.out.println(nos.numIslands(grid));
	}

}

class UnionFind {
	int count; // # of connected components
	int[] parent;
	int[] rank;

	public UnionFind(char[][] grid) { // for problem 200
		count = 0;
		int m = grid.length;
		int n = grid[0].length;
		parent = new int[m * n];
		rank = new int[m * n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (grid[i][j] == '1') {
					//index of parent, set to itself first
					parent[i * n + j] = i * n + j;
					//count how many 1 there
					++count;
				}
				//level of each union, default to 0
				rank[i * n + j] = 0;
			}
		}
	}

	public int find(int i) { // path compression
		if (parent[i] != i) {
			parent[i] = find(parent[i]);
		}
		return parent[i];
	}

	public void union(int x, int y) { // union with rank
		int rootx = find(x);
		int rooty = find(y);
		if (rootx != rooty) {
			if (rank[rootx] > rank[rooty]) {
				parent[rooty] = rootx;
			} else if (rank[rootx] < rank[rooty]) {
				parent[rootx] = rooty;
			} else {
				parent[rooty] = rootx;
				rank[rootx] += 1;
			}
			--count;
		}
	}

	public int getCount() {
		return count;
	}
}
