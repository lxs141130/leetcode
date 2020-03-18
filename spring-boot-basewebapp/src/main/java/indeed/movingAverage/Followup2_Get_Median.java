package indeed.movingAverage;

import java.util.Deque;
import java.util.LinkedList;

import indeed.movingAverage.Followup1_No_Memory.Event;

/**
 * @author franksun
 * 
 * Mar 18, 2020
 * 
 */
public class Followup2_Get_Median {
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
	
	//实现find Median，其实O1操作的话，要始终维护两个heap，这样塞进去会很慢
    //原有基础上实现的话，那就直接quick select的办法了。
    //复杂度是On，因为每次average case是去掉一半，就是O(n)+O(n/2)+O(n/4)+... 最后出来是O(2n)
    //那这个需要把整个queue给倒出来再塞回去。
	public double getMedian(){
		removeExpireEvent(getNow());
		int[] temp = new int[queue.size()];
        for (int i = 0; i<queue.size(); i++){
            temp[i] = queue.poll().val;
        }
        //这里还得把queue还原回去,先不写了。
        int len = temp.length;
        if (len % 2 == 0){
            return 0.5*(findKth(temp, len/2, 0, len-1) + findKth(temp, len/2-1, 0, len-1));
        }
        return (double)findKth(temp, len/2, 0, len-1);
    }

    public int findKth(int[] temp, int k, int start, int end){
        int pivot = temp[start];
        int left = start, right = end;
        while (left < right){
            while (temp[right] > pivot && left < right){
                right--;
            }
            while (temp[left] <= pivot && left < right){
                left++;
            }
            swap(temp, left, right);
        }
        swap(temp, start, right);
        if (k == right){
            return pivot;
        }
        else if (k < right){
            return findKth(temp, k, start, right-1);
        }
        return findKth(temp, k, right+1, end);
    }

    public void swap(int[] temp, int left, int right){
        int i = temp[left];
        temp[left] = temp[right];
        temp[right] = i;
    }
	
	
}
