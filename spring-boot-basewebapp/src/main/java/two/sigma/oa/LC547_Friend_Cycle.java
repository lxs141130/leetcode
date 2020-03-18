package two.sigma.oa;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author franksun
 * 
 *         Feb 20, 2020
 * 
 */
public class LC547_Friend_Cycle {
	public int findCircleNum(int[][] M) {
		int n = M.length;
		Union u = new Union(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (M[i][j] == 1 && i != j) {
					u.union(i, j);
				}
			}
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (u.parents[i] == -1) {
				count++;
			}
		}
		return count;
	}

	class Union {
		int[] parents;

		public Union(int n) {
			parents = new int[n];
			Arrays.fill(parents, -1);
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			if (rootX != rootY) {
				parents[rootY] = rootX;
			}
		}

		public int find(int index) {
			if (parents[index] == -1) {
				return index;
			}
			return find(parents[index]);
		}
	}

	public int findCircleNumBFS(int[][] M) {
		int n = M.length;
		int[] visited = new int[n];
		Queue<Integer> q = new LinkedList<>();
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				q.add(i);
				while (!q.isEmpty()) {
					int index = q.poll();
					visited[index] = 1;
					for (int j = 0; j < n; j++) {
						if (M[i][j] == 1 && visited[j] == 0) {
							q.add(j);
						}
					}
				}
				count++;
			}
		}
		return count;
	}

	public int findCircleNumDFS(int[][] M) {
		int n = M.length;
		int[] visited = new int[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				dfs(M, visited, i);
				count++;
			}
		}
		return count;
	}

	public void dfs(int[][] M, int[] visited, int i) {
		for (int j = 0; j < M.length; j++) {
			if (M[i][j] == 1 && visited[j] == 0) {
				visited[j] = 1;
				dfs(M, visited, j);
			}
		}
	}

	public static void main(String[] args) {
		LC547_Friend_Cycle fc = new LC547_Friend_Cycle();
		System.out.println(fc.findCircleNum(new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }));
	}
}
