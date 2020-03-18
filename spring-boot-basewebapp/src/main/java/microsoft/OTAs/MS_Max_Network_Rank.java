package microsoft.OTAs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author franksun
 * 
 *         Feb 11, 2020
 * 
 */

//time -> O(M) M is the size for A/B
//space -> O(M)
public class MS_Max_Network_Rank {
	public static void main(String[] args) {
		System.out.println(countEdges(new int[] { 1, 2, 3, 3 }, new int[] { 2, 3, 1, 4 }, 4));
		System.out.println(countEdges(new int[] { 1, 2, 4, 5 }, new int[] { 2, 3, 5, 6 }, 6));

		System.out.println(countEdges1(new int[] { 1, 2, 3, 3 }, new int[] { 2, 3, 1, 4 }, 4));
		System.out.println(countEdges1(new int[] { 1, 2, 4, 5 }, new int[] { 2, 3, 5, 6 }, 6));
	}

	public static int countEdges(int[] A, int[] B, int N) {
		if (A == null || B == null || A.length == 0 || B.length == 0 || A.length != B.length) {
			return 0;
		}

		// Form an adjacency list
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < A.length; i++) {
			map.putIfAbsent(A[i], new ArrayList<>());
			map.get(A[i]).add(B[i]);
			map.putIfAbsent(B[i], new ArrayList<>());
			map.get(B[i]).add(A[i]);
		}

		// Iterate through all nodes and perform DFS on the node not present in seen set
		Set<Integer> seen = new HashSet<>();
		int res = 0;
		for (int j = 1; j <= N; j++) {
			if (!seen.contains(j)) {
				int edges = dfs(seen, map, j);
				res = Math.max(res, edges);
			}
		}
		// Since each edge is counted twice in the dfs method we return res/2
		return res / 2;
	}

	public static int dfs(Set<Integer> seen, Map<Integer, List<Integer>> map, int cur) {
		if (seen.contains(cur) || !map.containsKey(cur)) {
			return 0;
		}

		seen.add(cur);
		List<Integer> nodes = new ArrayList<>();
		nodes = map.get(cur);
		int res = nodes.size();

		for (Integer node : nodes) {
			if (!seen.contains(node)) {
				res += dfs(seen, map, node);
			}
		}

		return res;
	}

	private static int countEdges1(int[] A, int[] B, int N) {
		if (A == null || B == null || A.length == 0 || B.length == 0 || A.length != B.length) {
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < A.length; i++) {
			map.putIfAbsent(A[i], 0);
			map.put(A[i], map.get(A[i]) + 1);
			map.putIfAbsent(B[i], 0);
			map.put(B[i], map.get(B[i]) + 1);

		}
		int max = 0;
		for (int i = 0; i < A.length; i++) {
			max = Math.max(map.get(A[i]) + map.get(B[i]) - 1, max);// extra -1 as the road is counted twice
		}
		return max;
	}
}
