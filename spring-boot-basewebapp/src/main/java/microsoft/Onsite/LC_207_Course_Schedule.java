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
 * Feb 18, 2020
 * 
 */
public class LC_207_Course_Schedule {
	
	Map<Integer, List<Integer>> schedule = new HashMap<>();
	
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		
		if (prerequisites == null || prerequisites.length == 0 || numCourses == 0) {
			return true;
		}
		//track visited course
		int[] visited = new int[numCourses];
		
		for (int[] prerequisite : prerequisites) {
			List<Integer> list = schedule.getOrDefault(prerequisite[1], new ArrayList<>());
			list.add(prerequisite[1]);
			schedule.put(prerequisite[1], list);
		}
		
		for (int i = 0; i < numCourses; i++) {
			if (!canFinishDFS(visited, i)) {
				return false;
			}
		}
		return true;

	}


	private boolean canFinishDFS(int[] visited, int i) {
		// TODO Auto-generated method stub
		if (visited[i] == -1) {
			return false;
		}
		if (visited[i] == 1) {
			return true;
		}
		visited[i] = -1;
		if (schedule.containsKey(i)) {
			for (int course : schedule.get(i)) {
				if (!canFinishDFS(visited, course)) {
					return false;
				}
			}
		}
		visited[i] = 1;
		return true;
	}
	
	public boolean canFinish1(int numCourses, int[][] prerequisites) {
		if (prerequisites == null || prerequisites.length == 0 || numCourses == 0) {
			return true;
		}
		int len = prerequisites.length;
		int[] pCounter = new int[numCourses];
		for (int[] prerequistie : prerequisites) {
			pCounter[prerequistie[0]]++;
		}
		int courseNoPre = 0;
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (pCounter[i] == 0) {
				q.add(i);
				courseNoPre++;
			}
		}
		
		while(!q.isEmpty()) {
			int course = q.poll();
			for (int i = 0; i < len; i++) {
				if (prerequisites[i][1] == course) {
					pCounter[prerequisites[i][0]]--;
					if (pCounter[prerequisites[i][0]] == 0) {
						q.add(prerequisites[i][0]);
						courseNoPre++;
					}
				}
			}
		}
		
		return courseNoPre == numCourses;
	}
	
		
}
