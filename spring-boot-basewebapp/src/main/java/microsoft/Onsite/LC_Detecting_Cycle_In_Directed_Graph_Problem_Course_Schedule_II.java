package microsoft.Onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franksun
 * 
 *         Feb 19, 2020
 * 
 */
public class LC_Detecting_Cycle_In_Directed_Graph_Problem_Course_Schedule_II {

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		DetectCycleGraph graph = new DetectCycleGraph(numCourses);
		for (int i = 0; i < prerequisites.length; i++) {
			int[] sub = prerequisites[i];
			graph.addEdge(sub[0], sub[1]);
		}
		return graph.isCycle(graph);

	}

	public static void main(String[] args) {
		LC_Detecting_Cycle_In_Directed_Graph_Problem_Course_Schedule_II dc = new LC_Detecting_Cycle_In_Directed_Graph_Problem_Course_Schedule_II();
		int numCourses = 4;
		int[][] prerequisites = new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 3 } };
		System.out.println(dc.findOrder(numCourses, prerequisites));
	}

	class DetectCycleGraph {

		private int nVertices;
		private List<List<Integer>> edges;

		public DetectCycleGraph(int n) {
			nVertices = n;
			edges = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				edges.add(new ArrayList<>());
			}
		}

		public DetectCycleGraph() {}

		private void addEdge(int i, int j) {
			edges.get(i).add(j);
		}

		List<Integer> rst = new ArrayList<>();

		public int[] isCycle(DetectCycleGraph graph) {

			boolean[] visited = new boolean[nVertices];
			boolean[] curRec = new boolean[nVertices];
			for (int i = 0; i < nVertices; i++) {
				if (isCycleUtil(visited, curRec, i))
					return new int[0];
			}

			int[] res = new int[nVertices];
			int index = 0;
			for (Integer i : rst) {
				res[index] = i;
				index++;
			}

			return res;
		}

		public boolean isCycleUtil(boolean[] visited, boolean[] curRec, int i) {

			if (!visited[i]) {

				visited[i] = true;
				curRec[i] = true;

				List<Integer> neighbours = edges.get(i);
				for (Integer v : neighbours) {
					//self cycle detected-> curRec[v]
					if (!visited[v] && isCycleUtil(visited, curRec, v) || curRec[v]) {
						return true;
					}
				}
				rst.add(new Integer(i));
			}

			curRec[i] = false;

			return false;
		}
	}
}
