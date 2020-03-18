package microsoft.Onsite;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author franksun
 * 
 * Mar 2, 2020
 * 
 */
public class LC_56_Merge_Intervals {
	public int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length <= 1) {
			return intervals;
		}
		Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
			
		LinkedList<int[]> merged = new LinkedList<>();
		for (int[] interval : intervals) {
			if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
				merged.add(interval);
			} else {
				merged.getLast()[1] = Math.max(interval[1], merged.getLast()[1]);
			}
		}
		
		return merged.toArray(new int[merged.size()][]);	
	}
	
	public static void main(String[] args) {
		int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
		LC_56_Merge_Intervals mi = new LC_56_Merge_Intervals();
		System.out.println(mi.merge(intervals));

	}
	
	
}
