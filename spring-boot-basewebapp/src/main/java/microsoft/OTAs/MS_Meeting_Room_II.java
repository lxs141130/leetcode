package microsoft.OTAs;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author franksun
 * 
 *         Feb 13, 2020
 * 
 */

//time -> O(Nlog(N))
//space -> O(N)
public class MS_Meeting_Room_II {
	public static int minMeetingRooms1(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		pq.add(intervals[0][1]);
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] >= pq.peek()) {
				pq.poll();
			}
			pq.add(intervals[i][1]);
		}

		return pq.size();
	}

	// time -> O(NlogN)
	// space -> O(N)
	public static int minMeetingRooms(int[][] intervals) {
		if (intervals == null)
			return 0;

		int rooms = 0;

		int[] start = new int[intervals.length];
		int[] end = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			start[i] = intervals[i][0];
			end[i] = intervals[i][1];
		}
		Arrays.sort(start);
		Arrays.sort(end);
		int k = 0;
		for (int j = 0; j < start.length; j++) {
//        	When we encounter an ending event, that means that some meeting that started earlier has ended now. We are not really concerned with which meeting has ended. All we need is that some meeting ended thus making a room available.
			if (start[j] < end[k])
				rooms++;
			else
				k++;
		}

		return rooms;
	}

	public static void main(String[] args) {
		// 2
		System.out.println(minMeetingRooms(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } }));
		// 1
		System.out.println(minMeetingRooms(new int[][] { { 7, 10 }, { 2, 4 } }));
		// 4
		System.out.println(
				minMeetingRooms(new int[][] { { 1, 10 }, { 2, 7 }, { 3, 19 }, { 11, 30 }, { 10, 20 }, { 8, 12 } }));
	}
}
