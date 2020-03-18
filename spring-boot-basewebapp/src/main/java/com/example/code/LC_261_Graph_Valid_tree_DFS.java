package com.example.code;

import java.util.ArrayList;
import java.util.List;

public class LC_261_Graph_Valid_tree_DFS {
	//DFS O(M + N)
	public boolean validTree(int n, int[][] edges) {
        //initialize adjacency list
		List<List<Integer>> adjList = new ArrayList<>(n);
		
		//initialize vertices
		for (int i = 0; i < n; i++) {
			adjList.add(i, new ArrayList<>());
		}
		
		//add edges
		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}
		
		boolean[] visited = new boolean[n];
		
		//make sure there is no cycle
		if (hasCycle(adjList, 0, visited, -1)) {
			return false;
		}
		
		// make sure all vertices are connected
        for (int i = 0; i < n; i++) {
            if (!visited[i]) 
                return false;
        }
        
        return true;
		
    }
    
	// check if an undirected graph has cycle started from vertex u
    private boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
		visited[u] = true;
		List<Integer> edges = adjList.get(u);
		for (int i = 0; i < edges.size(); i++) {
			int v = edges.get(i);
			
			if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, visited, u))) {
				return true;
			}
			
		}
		
		return false;
	}

	public static void main(String[] args) {
    	LC_261_Graph_Valid_tree_DFS gvt = new LC_261_Graph_Valid_tree_DFS();
    	int n = 4;
    	int[][] edges = {{0,1},{2,3},{1,2}};
    	System.out.print(gvt.validTree(n, edges));
	}
}
