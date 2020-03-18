package com.example.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC_207_Course_Schedule {

	public boolean canFinish1(int numCourses, int[][] prerequisites) {
		
		if (prerequisites == null || numCourses == 0 || prerequisites.length == 0) {
			return true;
		}
		int len = prerequisites.length;
		// counter for number of prerequisites
		int[] pCounter = new int[len];
		for (int i = 0; i < len; i++) {
			pCounter[prerequisites[i][0]]++;
		}
		//store courses that have no prerequisites
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			if (pCounter[i] == 0) {
				queue.add(i);
			}
		}
		// number of courses that have no prerequisites
	    int numNoPre = queue.size();
	    while (!queue.isEmpty()) {
	    	int top = queue.poll();
	    	for (int i = 0; i < len; i++) {
	    		// if a course's prerequisite can be satisfied by a course in queue
	    		if (prerequisites[i][1] == top) {
	    			pCounter[prerequisites[i][0]]--;
	    			if (pCounter[prerequisites[i][0]] == 0) {
	    				numNoPre++;
	                    queue.add(prerequisites[i][0]);
	    			}
	    		}
	    	}
	    }
		return numNoPre == numCourses;
	}
	
	// use the map to store what courses depend on a course 
    HashMap<Integer,List<Integer>> map = new HashMap<>();
    
	public boolean canFinish2(int numCourses, int[][] prerequisites) {
		if (prerequisites == null || numCourses == 0 || prerequisites.length == 0) {
			return true;
		}
		//track visited courses
	    int[] visited = new int[numCourses];
	    
		for (int[] prerequisite : prerequisites) {
			List<Integer> list = map.getOrDefault(prerequisite[1], new ArrayList<>());
			list.add(prerequisite[0]);
			map.put(prerequisite[1], list);
		}
		
		for (int i = 0; i < numCourses; i++) {
			if (!canFinishDFS(visited,i)) {
				return false;
			}
		}
		return true;
	}

	private boolean canFinishDFS(int[] visited, int i) {
		if (visited[i] == -1) {
			return false;
		}
		if (visited[i] == 1) {
			return true;
		}
		visited[i] = -1;
		if(map.containsKey(i)){
	        for(int j: map.get(i)){
	            if(!canFinishDFS(visited, j)) 
	                return false;
	        }
	    }
	 
	    visited[i] = 1;
	 
	    return true;
	}
}

//0 = [1]

//2, [[1,0]] 