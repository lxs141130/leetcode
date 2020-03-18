package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Mar 4, 2020
 * 
 */
public class LC_155_MIN_Stack {
	Node top;

	/** initialize your data structure here. */
	public LC_155_MIN_Stack() {
		top = null;
	}

	public void push(int x) {
		if (top == null) {
			top = new Node(x, x);
		} else {
			int minVal = Math.min(top.minVal, x);
			Node node = new Node(minVal, x);
			node.next = top;
			top = node;
		}
	}

	public void pop() {
		if (top == null) {
			return;
		} else {
			top = top.next;
		}
	}

	public int top() {
		if (top == null) {
			return -1;
		} else {
			return top.val;
		}
	}

	public int getMin() {
		if (top == null) {
			return -1;
		} else {
			return top.minVal;
		}
	}

	class Node {
		int minVal;
		int val;
		Node next;

		public Node(int minVal, int val) {
			this.minVal = minVal;
			this.val = val;
		}
	}

}
