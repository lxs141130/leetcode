package microsoft.Onsite;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author franksun
 * 
 * Feb 26, 2020
 * 
 */
public class LC_346_Moving_Average {
	
	Queue<Integer> q;
	int windowSize;
    int windowSum;
	
	public LC_346_Moving_Average(int size) {
		q = new LinkedList<>();
		windowSize = size;
		windowSum = 0;
	}
	
	public double next(int val) {
		q.offer(val);
		windowSum += val;
		if (q.size() > windowSize) {
			windowSum -= q.poll();
		}
		return windowSum * 1.0 / q.size();
	}
}
