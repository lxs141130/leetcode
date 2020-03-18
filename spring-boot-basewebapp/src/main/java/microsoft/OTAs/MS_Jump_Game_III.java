package microsoft.OTAs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author franksun
 * 
 * Feb 12, 2020
 * 
 */
//https://leetcode.com/problems/jump-game-iii/

//time O(N)
//space O(N)
public class MS_Jump_Game_III {
	public static boolean canReach(int[] arr, int start) {
		if (arr == null || arr.length == 0 || start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        int n = arr.length;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        boolean[] visited = new boolean[n];
        while (!q.isEmpty()) {
            int i = q.poll();
            if (arr[i] == 0) return true; // found result
            if (!visited[i]) {
                visited[i] = true;
                if (i + arr[i] < n) q.offer(i + arr[i]);
                if (i - arr[i] >= 0) q.offer(i - arr[i]);
            }
        }
        return false;
    }
	
	public static void main(String[] args) {
		System.out.println(canReach(new int[] {4,2,3,0,3,1,2}, 5));
		System.out.println(canReach(new int[] {4,2,3,0,3,1,2}, 0));
		System.out.println(canReach(new int[] {3,0,2,1,2}, 2));
	}
}
