package microsoft.Onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franksun
 * 
 *         Mar 2, 2020
 * 
 */
public class LC_261_Graph_Valid_Tree {
	public boolean validTree(int n, int[][] edges) {
		List<List<Integer>> adjList = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			adjList.set(i, new ArrayList<>());
		}
		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}
		boolean[] visited = new boolean[n];
		if (hasCycle(adjList, 0, -1, visited)) {
			return false;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	private boolean hasCycle(List<List<Integer>> adjList, int cur, int parent, boolean[] visited) {
		visited[cur] = true;
		for (int i = 0; i < adjList.get(cur).size(); i++) {
			int v = adjList.get(cur).get(i);
			if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, cur, visited))) {
				return true;
			}
		}
		return false;
	}
}
