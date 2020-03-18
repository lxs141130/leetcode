package microsoft.Onsite;

import java.util.Arrays;

/**
 * @author franksun
 * 
 *         Mar 4, 2020
 * 
 */
public class LC_252_Meeting_Rooms {
	public boolean canAttendMeetings(int[][] intervals) {
		Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

		for (int i = 0; i < intervals.length - 1; i++) {
			if (intervals[i][1] > intervals[i + 1][0])
				return false;
		}
		return true;
	}
}
