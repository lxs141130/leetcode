package indeed;

import java.util.TreeMap;

/**
 * @author franksun
 * 
 *         Feb 8, 2020
 * 
 */
public class LC_362_Design_Hit_Counter {
	TreeMap<Integer, Integer> map;
	int lastTS;

	/** Initialize your data structure here. */
	public LC_362_Design_Hit_Counter() {
	    map = new TreeMap<>();
	    map.put(0,0);
	    lastTS = 0;
	}

	/**
	 * Record a hit.
	 * 
	 * @param timestamp - The current timestamp (in seconds granularity).
	 */
	public void hit(int timestamp) {
		map.put(timestamp, map.get(lastTS) + 1);
		if (timestamp != lastTS) {
			lastTS = timestamp;
		}
	}

	/**
	 * Return the number of hits in the past 5 minutes.
	 * 
	 * @param timestamp - The current timestamp (in seconds granularity).
	 */
	public int getHits(int timestamp) {
		int old = Math.max(0, timestamp - 300);
		timestamp = Math.min(timestamp, lastTS); // if timestamp is out of the stored values, then use lastTS
		return map.get(timestamp) - map.get(map.floorKey(old));
	}
	
	public static void main(String[] args) {
		LC_362_Design_Hit_Counter counter = new LC_362_Design_Hit_Counter();

		// hit at timestamp 1.
		counter.hit(1);

		// hit at timestamp 2.
		counter.hit(2);

		// hit at timestamp 3.
		counter.hit(3);

		// get hits at timestamp 4, should return 3.
		System.out.println(counter.getHits(4));

		// hit at timestamp 300.
		counter.hit(300);

		// get hits at timestamp 300, should return 4.
		System.out.println(counter.getHits(300));

		// get hits at timestamp 301, should return 3.
		System.out.println(counter.getHits(301));
	}
}