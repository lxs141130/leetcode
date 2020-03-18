package com.example.code;

import java.util.Arrays;

public class LC_261_Graph_Valid_tree_Union_Find {
	//UnionFind O(M * log(N)) M denotes the edges number | log(N) : cost for every union
	public boolean validTree(int n, int[][] edges) {
        Union u = new Union(edges, n);
        int r = edges.length;
        for (int i = 0; i < r; i++) {
            if (u.find(edges[i][0]) == u.find(edges[i][1])) {
                return false;
            }
            u.union(edges[i][0], edges[i][1]);
        }
        
        return edges.length == n - 1;
    }
    
    class Union {
        int[] parents;
        
        Union (int[][] edge, int n){
            parents = new int[n];
            Arrays.fill(parents, -1);
        }
        
        public int find (int index) {
            if (parents[index] == -1) {
                return index;
            }
            return find(parents[index]);
        }
        
        public void union (int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            parents[rootB] = rootA;
        }
    }
    
    public static void main(String[] args) {
    	LC_261_Graph_Valid_tree_Union_Find gvt = new LC_261_Graph_Valid_tree_Union_Find();
    	int n = 4;
    	int[][] edges = {{0,1},{2,3},{1,2}};
    	System.out.print(gvt.validTree(n, edges));
	}
}
