package indeed;

import java.util.Arrays;

/**
 * @author franksun
 * 
 *         Mar 16, 2020
 * 
 */
public class LC_274_H_Index {
	public int hIndex(int[] citations) {
		// sorting the citations in ascending order
		Arrays.sort(citations);
		// finding h-index by linear search
		int i = 0;
		// as we sort ascending, mapping to descending, we take i -> len -i - 1
		while (i < citations.length && citations[citations.length - 1 - i] > i) {
			i++;
		}
		return i; // after the while loop, i = i' + 1
	}
}
