package indeed.movingAverage;

import java.util.LinkedList;

/**
 * @author franksun
 * 
 *         Mar 17, 2020
 * 
 */
public class Moving_Average {

	class Event {
		long time;
		int val;

		public Event(long time, int val) {
			this.time = time;
			this.val = val;
		}
	}

	private LinkedList<Event> queue = new LinkedList<>();
	private long sum = 0;

	public void record(int val) {
		queue.add(new Event(getNow(), val));
		sum += val;
	}
	
	private long getNow() {
		return System.currentTimeMillis() / 1000;
	}

	public double getAvg() {
		//去掉过期数据
		long currentTime = getNow();
		while (!queue.isEmpty() && currentTime - queue.peek().time > 300) {
			sum -= queue.remove().val;
		}

		return sum / queue.size();
	}
}
