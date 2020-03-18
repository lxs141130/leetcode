package indeed.getNumberInAtLeastKStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author franksun
 * 
 *         Mar 17, 2020
 * 
 */
class Stream {
	Iterator<Integer> iterator;

	public Stream(List<Integer> list) {
		this.iterator = list.iterator();
	}

	public boolean move() {
		return iterator.hasNext();
	}

	public int getValue() {
		return iterator.next();
	}
}

class Data {
	int val;
	Stream stream;

	public Data(Stream stream) {
		this.stream = stream;
		this.val = stream.getValue();
	}
}

public class MergeKSortedStreams {

	public List<Integer> getNumberInAtLeastKStream(List<Stream> lists, int k) {
		List<Integer> res = new ArrayList<>();
		if (lists == null || lists.size() == 0)
			return res;
		PriorityQueue<Data> minHeap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
		// add all elements into minHeap
		for (Stream s : lists) {
			if (s.move()) {
				minHeap.offer(new Data(s));
			}
		}
		
		if (minHeap.size() < k) {
			return res;
		}

		while (!minHeap.isEmpty()) {
			Data cur = minHeap.poll();
			int curValue = cur.val;
			int count = 1;
			// iterate over the first Num to get next valid value,
			// update cur Num with the next value
			while (cur.stream.move()) {
				int nextVal = cur.stream.getValue();
				// ignore the same value existed in the same Num
				if (nextVal == curValue) {
					continue;
				} else {
					cur.val = nextVal;
					minHeap.offer(cur);
					break;
				}
			}
			// check how many count of curValue existed in other Num
			while (!minHeap.isEmpty() && curValue == minHeap.peek().val) {
				count++;
				Data num = minHeap.poll();
				// same here, ignore same value in the same Num
				while (num.stream.move()) {
					int nextVal = num.stream.getValue();
					if (curValue == nextVal) {
						continue;
					} else {
						num.val = nextVal;
						minHeap.offer(num);
						break;
					}
				}
			}
			// if count is larger than k, means it appeared in every stream, add to result
			// list
			if (count >= k) {
				res.add(curValue);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		MergeKSortedStreams test = new MergeKSortedStreams();
		Integer[] arr1 = { -1, 3, 5, 6, 6, 7, 9, 12, 51 };
		Integer[] arr2 = { 0, 3, 5, 5, 9, 13, 15, 51 };
		Integer[] arr3 = { 5, 5, 7, 11, 19, 21, 51 };

		List<Integer> l1 = Arrays.asList(arr1);
		List<Integer> l2 = Arrays.asList(arr2);
		List<Integer> l3 = Arrays.asList(arr3);

		List<Stream> lists = new ArrayList<>();
		lists.add(new Stream(l1));
		lists.add(new Stream(l2));
		lists.add(new Stream(l3));

		List<Integer> res = test.getNumberInAtLeastKStream(lists, 3);
		System.out.println(res);
	}
}