package com.example.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LC_210_Course_Schedule2 {

	public int[] findOrder1(int numCourses, int[][] prerequisites) {
		int[] rst = new int[numCourses];

		if (prerequisites == null) {
			throw new IllegalArgumentException("illegal prerequisites array");
		}

		int len = prerequisites.length;

		// if there is no prerequisites, return a sequence of courses
		if (len == 0) {
			for (int m = 0; m < numCourses; m++) {
				rst[m] = m;
			}
			return rst;
		}

		Queue<Integer> q = new LinkedList<>();
		int[] pCounter = new int[numCourses];
		// count pre class
		for (int i = 0; i < len; i++) {
			pCounter[prerequisites[i][0]]++;
		}
		// add class without pre to q
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
			for (int[] pre : prerequisites) {
				if (pre[1] == top) {
					pCounter[pre[0]]--;
					if (pCounter[pre[0]] == 0) {
						q.add(pre[0]);
						numNoPre++;
					}
				}
			}
		}

		return numNoPre == numCourses ? rst : new int[0];
	}

	
	
	static int WHITE = 1;
	static int GRAY = 2;
	static int BLACK = 3;

	boolean isPossible;
	Map<Integer, Integer> color;
	Map<Integer, List<Integer>> adjList;
	List<Integer> topologicalOrder;

	private void init(int numCourses) {
		this.isPossible = true;
		this.color = new HashMap<Integer, Integer>();
		this.adjList = new HashMap<Integer, List<Integer>>();
		this.topologicalOrder = new ArrayList<Integer>();

		// By default all vertices are WHITE
		for (int i = 0; i < numCourses; i++) {
			this.color.put(i, WHITE);
		}
	}

	private void dfs(int node) {

		// Don't recurse further if we found a cycle already
		if (!this.isPossible) {
			return;
		}

		// Start the recursion
		this.color.put(node, GRAY);

		// Traverse on neighboring vertices
		for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
			if (this.color.get(neighbor) == WHITE) {
				this.dfs(neighbor);
			} else if (this.color.get(neighbor) == GRAY) {
				// An edge to a GRAY vertex represents a cycle
				this.isPossible = false;
			}
		}

		// Recursion ends. We mark it as black
		this.color.put(node, BLACK);
		this.topologicalOrder.add(node);
	}

	public int[] findOrder2(int numCourses, int[][] prerequisites) {

		this.init(numCourses);

		// Create the adjacency list representation of the graph
		for (int i = 0; i < prerequisites.length; i++) {
			int dest = prerequisites[i][0];
			int src = prerequisites[i][1];
			List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
			lst.add(dest);
			adjList.put(src, lst);
		}

		// If the node is unprocessed, then call dfs on it.
		for (int i = 0; i < numCourses; i++) {
			if (this.color.get(i) == WHITE) {
				this.dfs(i);
			}
		}

		int[] order;
		if (this.isPossible) {
			order = new int[numCourses];
			for (int i = 0; i < numCourses; i++) {
				order[i] = this.topologicalOrder.get(numCourses - i - 1);
			}
		} else {
			order = new int[0];
		}

		return order;
	}

	public static void main(String[] args) {
		LC_210_Course_Schedule2 cs2 = new LC_210_Course_Schedule2();
		int numCourses = 4;
		int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		int[] rst = cs2.findOrder1(numCourses, prerequisites);
		for (int i : rst) {
			System.out.print(i + " ");
		}
	}
}