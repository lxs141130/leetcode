package microsoft.Onsite;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author franksun
 * 
 *         Mar 4, 2020
 * 
 */
public class LC_225_Implement_Stack_Use_Queue {
	Queue<Integer> q;

	/** Initialize your data structure here. */
	public LC_225_Implement_Stack_Use_Queue() {
		q = new LinkedList<>();
	}

	/** Push element x onto stack. */
	public void push(int x) {
		q.add(x);
	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
		int size = q.size();
		if (size == 0) {
			return -1;
		}
		while (size > 1) {
			q.add(q.poll());
			size--;
		}
		return q.poll();
	}

	/** Get the top element. */
	public int top() {
		int size = q.size();
		if (size == 0) {
			return -1;
		}
		while (size > 1) {
			q.add(q.poll());
			size--;
		}
		int top = q.peek();
		q.add(q.poll());
		return top;
	}

	/** Returns whether the stack is empty. */
	public boolean empty() {
		return q.isEmpty();
	}
}