package amazonOA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriticalRouters {
	/**
	 * You are given an undirected connected graph. An articulation point (or cut
	 * vertex) is defined as a vertex which, when removed along with associated
	 * edges, makes the graph disconnected (or more precisely, increases the number
	 * of connected components in the graph). The task is to find all articulation
	 * points in the given graph.
	 * 
	 * Input: The input to the function/method consists of three arguments:
	 * 
	 * numNodes, an integer representing the number of nodes in the graph. numEdges,
	 * an integer representing the number of edges in the graph. edges, the list of
	 * pair of integers - A, B representing an edge between the nodes A and B.
	 * Output: Return a list of integers representing the critical nodes.
	 * 
	 * Example:
	 * 
	 * Input: numNodes = 7, numEdges = 7, edges = [[0, 1], [0, 2], [1, 3], [2, 3],
	 * [2, 5], [5, 6], [3, 4]]
	 * 
	 */
	static int time = 0;// time when discover each node

	public static void main(String[] args) {
		int numRouters1 = 7;// number of node
		int numLinks1 = 7;// number of link
		int[][] links1 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 2, 5 }, { 5, 6 }, { 3, 4 } };
		System.out.println(getCriticalNodes(links1, numLinks1, numRouters1));
	}

	private static List<Integer> getCriticalNodes(int[][] links, int numLinks, int numRouters) {
		time = 0;
		Map<Integer, Set<Integer>> map = new HashMap<>();// node, nodes which is connected with cur node
		for (int i = 0; i < numRouters; i++) {
			map.put(i, new HashSet<>());
		}
		for (int[] link : links) {
			map.get(link[0]).add(link[1]);
			map.get(link[1]).add(link[0]);
		}
		Set<Integer> set = new HashSet<>();// res set
		int[] low = new int[numRouters];// low[u] records the lowest node u can reach
		int[] ids = new int[numRouters];// visited
		int parent[] = new int[numRouters];// every node has one connected node as parent
		Arrays.fill(ids, -1);
		Arrays.fill(parent, -1);
		for (int i = 0; i < numRouters; i++) {
			if (ids[i] == -1)
				dfs(map, low, ids, parent, i, set);
		}
		return new ArrayList<>(set);
	}

	private static void dfs(Map<Integer, Set<Integer>> map, int[] low, int[] ids, int[] parent, int cur,
			Set<Integer> res) {
		int children = 0;
		ids[cur] = low[cur] = ++time;// discover node, low[u] records the lowest vertex u can reach
		for (int nei : map.get(cur)) {// every connected node
			if (ids[nei] == -1) {// not visited
				children++;
				parent[nei] = cur;// mark cur is nei's parent
				dfs(map, low, ids, parent, nei, res);
				low[cur] = Math.min(low[cur], low[nei]);
				if ((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[nei] >= ids[cur]))
					// no parent, more child, if remove cur, can't reach other child
					// has parent, but the lowest node nei can reach >= cur, if remove cur, then nei
					// cannot be reached
					// u - v is critical, there is no path for v to reach back to u or previous
					// vertices of u
					res.add(cur);
			} else if (nei != parent[cur])// cur has other parent
				low[cur] = Math.min(low[cur], ids[nei]);
		}
	}
}
