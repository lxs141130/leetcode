package microsoft.Onsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author franksun
 * 
 *         Feb 18, 2020
 * 
 */

//Time Complexity:O(N) since we process each node exactly once and end up processing the entire graph given to us.
//Space Complexity:O(N) since we use an intermediate queue data structure to keep all the nodes with 0 in-degree.In the worst case,there won't be any prerequisite relationship and the queue will contain all the vertices initially since all of them will have 0 in-degree.

public class LC_210_Course_Schedule_II {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (prerequisites == null) {
			return new int[0];
		}
		int len = prerequisites.length;
		int[] rst = new int[numCourses];
		// corner case
		if (len == 0) {
			for (int i = 0; i < numCourses; i++) {
				rst[i] = i;
			}
			return rst;
		}

		int[] pCounter = new int[numCourses];
		for (int[] pre : prerequisites) {
			pCounter[pre[0]]++;
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (pCounter[i] == 0) {
				q.add(i);
			}
		}

		int i = 0;
		int numNoPre = q.size();

		while (!q.isEmpty()) {
			int top = q.poll();
			rst[i++] = top;
			for (int j = 0; j < prerequisites.length; j++) {
				if (prerequisites[j][1] == top) {
					pCounter[prerequisites[j][0]]--;
					if (pCounter[prerequisites[j][0]] == 0) {
						q.add(prerequisites[j][0]);
						numNoPre++;
					}
				}
			}
		}

		return numCourses == numNoPre ? rst : new int[0];
	}

	public int[] findOrder1(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();

		int[] indegree = new int[numCourses];
		int[] topologicalOrder = new int[numCourses];

		// Create the adjacency list representation of the graph
		for (int i = 0; i < prerequisites.length; i++) {
			int dest = prerequisites[i][0];
			int src = prerequisites[i][1];

			List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
			lst.add(dest);
			adjList.put(src, lst);

			// Record in-degree of each vertex
			indegree[dest]++;
		}

		// Add all vertices with 0 in-degree to the queue
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}
		int i = 0;
		// Process until the Q becomes empty
		while (!q.isEmpty()) {
			int node = q.remove();
			topologicalOrder[i++] = node;

			// Reduce the in-degree of each neighbor by 1
			if (adjList.containsKey(node)) {
				for (Integer neighbor : adjList.get(node)) {
					indegree[neighbor]--;

					// If in-degree of a neighbor becomes 0, add it to the Q
					if (indegree[neighbor] == 0) {
						q.add(neighbor);
					}
				}
			}
		}

		// Check to see if topological sort is possible or not.
		if (i == numCourses) {
			return topologicalOrder;
		}

		return new int[0];
	}
}
