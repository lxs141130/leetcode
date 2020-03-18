package com.example.code;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC_547_Friend_Circles {
	
	class Union {
		int[] parents;
		
		public Union (int n) {
			parents = new int[n];
			Arrays.fill(parents, -1);
		}
		
		public int find(int index) {
			if (parents[index] == -1) {
				return index;
			}
			return find(parents[index]);
		}
		
		public void union(int a, int b) {
			int rootA = find(a);
			int rootB = find(b);
			if (rootA != rootB) {
				parents[rootB] = rootA;
			}
		}
	}
	
	
	public int findCircleNum(int[][] M) {
		if (M == null || M.length ==0) {
			return 0;
		}
		Union union = new Union(M.length);
		
		for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) {
                	union.union(i, j);
                }
            }
        }
		
		int count = 0;
		for (int i = 0; i < M.length; i++) {
			if (union.parents[i] == -1) {
				count++;
			}
		}
		
		return count;
	}
	
	
	public int findCircleNum2(int[][] M) {
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
	

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
    public int findCircleNum3(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

	
	public static void main(String[] args) {
		LC_547_Friend_Circles fc = new LC_547_Friend_Circles();
//		[[1,1,0,0,0,0],[1,1,0,0,0,0],[0,0,1,1,1,0],[0,0,1,1,0,0],[0,0,1,0,1,0],[0,0,0,0,0,1]]
		int[][] M = {{1,1,0,0,0,0},{1,1,0,0,0,0},{0,0,1,1,1,0},{0,0,1,1,0,0},{0,0,1,0,1,0},{0,0,0,0,0,1}};
		System.out.print(fc.findCircleNum(M));
	}
}
