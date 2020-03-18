package microsoft.Onsite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author franksun
 * 
 *         Feb 19, 2020
 * 
 */
public class LC_Detecting_Cycle_In_Directed_Graph_Course_Schedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Graph g = new Graph(numCourses, prerequisites);
		return !g.isCyclic();
	}

	// Driver code
	public static void main(String[] args) {
		LC_Detecting_Cycle_In_Directed_Graph_Course_Schedule cs = new LC_Detecting_Cycle_In_Directed_Graph_Course_Schedule();
		System.out.println(cs.canFinish(2, new int[][] { { 1, 1 } }));
	}

	class Graph {

		private final int nVertices;
		private final List<List<Integer>> adj;

		public Graph(int V, int[][] prerequisites) {
			nVertices = V;
			adj = new ArrayList<>(V);

			for (int i = 0; i < V; i++) {
				adj.add(new LinkedList<>());
			}

			for (int i = 0; i < prerequisites.length; i++) {
				int u = prerequisites[i][0];
				int v = prerequisites[i][1];
				addEdge(u, v);
			}
		}

		private void addEdge(int source, int dest) {
			adj.get(source).add(dest);
		}

		// Returns true if the graph contains a
		// cycle, else false.
		// This function is a variation of DFS() in
		// https://www.geeksforgeeks.org/archives/18212
		private boolean isCyclic() {
			// Mark all the vertices as not visited and not part of recursion stack
			boolean[] visited = new boolean[nVertices];
			boolean[] recStack = new boolean[nVertices];

			// Call the recursive helper function to detect cycle in different DFS trees
			for (int i = 0; i < nVertices; i++) {
				if (isCyclicUtil(i, visited, recStack)) {
					return true;
				}
			}
			return false;
		}

		// This function is a variation of DFSUtil() in
		// https://www.geeksforgeeks.org/archives/18212
		private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {

			if (!visited[i]) {
				visited[i] = true;
				recStack[i] = true;
				
				List<Integer> children = adj.get(i);

				for (Integer c : children) {
					if (!visited[c] && isCyclicUtil(c, visited, recStack) || recStack[c]) {
						return true;
					}
				}
				
			}
			recStack[i] = false;
			return false;
		}
	}
}