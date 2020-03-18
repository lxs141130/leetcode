package microsoft.Onsite;

/**
 * @author franksun
 * 
 * Feb 21, 2020
 * 
 */
public class LC_695_Max_Area_of_Island {
	
	public int area(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0)
            return 0;
        grid[r][c] = 0;
        return (1 + area(grid, r+1, c) + area(grid, r-1, c)
                  + area(grid, r, c-1) + area(grid, r, c+1));
    }

    public int maxAreaOfIsland1(int[][] grid) {
        int ans = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    ans = Math.max(ans, area(grid, r, c));       
                }
            }
        }
        return ans;
    }
	
	public int maxAreaOfIsland(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		Union u = new Union(grid);
		
		int max = 0;
		
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					grid[i][j] = 0;
					for (int k = 0; k < 4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						if (x >= 0 && x < m && y >=0 && y < n && grid[x][y] == 1) {
							u.union(i * n + j, x * n + y);
						}
					}
				}
				
			}
		}
		
		for (int i = 0; i < m * n; i++) {
			if (u.parents[i] == i) {
				max = Math.max(max, u.size[i]);
			}
		}
		
		return max;
    }
	
	class Union{
		int[] parents;
		int[] ranks;
		int[] size;
		
		public Union(int[][] grid) {
			int m = grid.length;
			int n = grid[0].length;
			parents = new int[m * n];
			ranks = new int[m * n];
			size = new int[m * n];
			
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == 1) {
						parents[i * n + j] = i * n + j;
						size[i * n + j] = 1;
					}
				}
			}
		}
		
		public int find(int x) {
			if (parents[x] != x) {
				parents[x] = find(parents[x]);
			}
			return parents[x];
		}
		
		public void union(int x, int y) { // union with rank
			int rootx = find(x);
			int rooty = find(y);
			if (rootx != rooty) {
				if (ranks[rootx] > ranks[rooty]) {
					parents[rooty] = rootx;
					size[rootx] += size[rooty];
				} else if (ranks[rootx] < ranks[rooty]) {
					parents[rootx] = rooty;
					size[rooty] += size[rootx];
				} else {
					parents[rooty] = rootx;
					ranks[rootx]++;
					size[rootx] += size[rooty];
				}
			}
		}
	}
}
