package indeed;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author franksun
 * 
 *         Feb 8, 2020
 * 
 */
public class LC_346_Moving_Average_From_Data_Stream {

	int windowSize;
	int windowSum;
	Queue<Integer> q;

	/** Initialize your data structure here. */
	public LC_346_Moving_Average_From_Data_Stream(int size) {
		q = new LinkedList<>();
		windowSize = size;
		windowSum = 0;
	}

	public double next(int val) {
		q.offer(val);
		windowSum += val;
		if (q.size() > windowSize) {
			windowSum = windowSum - q.poll();
		}
		return windowSum * 1.0 / q.size();
	}
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size); double param_1 = obj.next(val);
 */
