package microsoft.OTAs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author franksun
 * 
 * Feb 12, 2020
 * 
 */

//https://leetcode.com/discuss/interview-question/430981/

//time -> O(nlog(k)) n is the number of buckets, k is T.length / n
//space -> O(n * k)
public class MS_Partition_array_into_N_subsets_with_balanced_sum {
	public static List<List<Integer>> part(int[] T, int n) {
		int[] sums = new int[n];
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return sums[a.intValue()] - sums[b.intValue()];
			}
		});
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			result.add(new ArrayList<>());
			pq.add(i);
		}
		
		for (int i = T.length - 1; i >= 0; i--) {
			int c = pq.poll();
			result.get(c).add(T[i]);
			sums[c] += T[i];
			pq.add(c);
		}
		
		return result;
	}

	
	public static void main(String[] args) {
		List<List<Integer>> result = part(new int[] {1,2,3,4,5,6,7,8,9,10}, 3);
		System.out.println(result);
		
		List<List<Integer>> result1 = part(new int[] {2,9,9,10,10}, 2);
		System.out.println(result1);
	}
}
