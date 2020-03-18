package microsoft.Onsite;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author franksun
 * 
 *         Feb 17, 2020
 * 
 */
public class LC_253_Meeting_Room_II {
	public  int minMeetingRooms(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		if (intervals.length == 1) {
			return 1;
		}
		int len = intervals.length;
		int[] starts = new int[len];
		int[] ends = new int[len];

		int i = 0;
		for (int[] interval : intervals) {
			starts[i] = interval[0];
			ends[i++] = interval[1];
		}
		Arrays.sort(starts);
		Arrays.sort(ends);

		int rst = 0;
		int k = 0;
		for (int j = 0; j < starts.length; j++) {
			if (starts[j] < ends[k]) {
				rst++;
			} else {
				k++;
			}
		}
		return rst;
	}

	public int minMeetingRooms2(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		if (intervals.length == 1) {
			return 1;
		}
		int len = intervals.length;
		//O(NlogN)
		Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(intervals[0][1]);

		for (int i = 1; i < len; i++) {
			if (!pq.isEmpty() && pq.peek() <= intervals[i][0]) {
				pq.poll();
			}
			pq.add(intervals[i][1]);
		}

		return pq.size();
	}

	public static void main(String[] args) {
		LC_253_Meeting_Room_II mr2 = new LC_253_Meeting_Room_II();
		// 2
		System.out.println(mr2.minMeetingRooms2(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } }));
		// 1
		System.out.println(mr2.minMeetingRooms2(new int[][] { { 7, 10 }, { 2, 4 } }));
		// 4
		System.out.println(
				mr2.minMeetingRooms2(new int[][] { { 1, 10 }, { 2, 7 }, { 3, 19 }, { 11, 30 }, { 10, 20 }, { 8, 12 } }));
	}
}
