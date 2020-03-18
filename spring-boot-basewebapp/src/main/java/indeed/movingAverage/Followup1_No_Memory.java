package indeed.movingAverage;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author franksun
 * 
 *         Mar 18, 2020
 * 
 */
public class Followup1_No_Memory {

	class Event {
		int val;
		long time;
		int size;

		public Event(int val, long time) {
			this.val = val;
			this.time = time;
			this.size = 1;
		}
	}

	private Deque<Event> queue = new LinkedList<>(); // 改成deque的话，可以从后面查
	private long sum = 0;
	int dataNum = 0;
	
	// 其实就是record这里有了两种办法，一种是建个新的，另一种就是合起来
	// if time interval is less than 10 s, we add to last element
	// else create a new records and insert it
	public void record(int val) {
		Event last = queue.peekLast();
		long currentTime = getNow();
		if (currentTime - last.time < 10) {
			last.size += 1;
			last.val += val;
		} else {
			Event event = new Event(val, currentTime);
			queue.offer(event);
		}
		dataNum += 1;
		sum += val;
		removeExpireEvent(currentTime);
	}
	
	// 这个是每次记录读进来的时间的,这个不用自己写,就是直接返回当前系统时间
	// this is a long value
	private long getNow() {
		return System.currentTimeMillis() / 1000;
	}
	
	private boolean isExpired(long curTime, long preTime) {
		return curTime - preTime > 300;
	}

	private void removeExpireEvent(Long currentTime) {
		while (!queue.isEmpty() && isExpired(currentTime, queue.peekFirst().time)) {
			Event curE = queue.poll();
			sum -= curE.val;
			dataNum -= curE.size;
		}
	}

	public double getAvg() {
		removeExpireEvent(getNow());
		if (!queue.isEmpty()) {
			return (double) sum / dataNum;
		}
		return 0.0;
	}
}
