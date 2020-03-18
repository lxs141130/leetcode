package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Feb 25, 2020
 * 
 */
public class LC_707_Design_LinkedList {
	public static void main(String[] args) {
		MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
		linkedList.addAtHead(1);
		linkedList.addAtTail(3);
		linkedList.addAtIndex(1, 2); // linked list becomes 1->2->3
		System.out.println(linkedList.get(1)); // returns 2
		linkedList.deleteAtIndex(1); // now the linked list is 1->3
		System.out.println(linkedList.get(1)); // returns 3
	}
}

class MyLinkedList {
	Node head;
	Node tail;
	int size;

	/** Initialize your data structure here. */
	public MyLinkedList() {
		head = new Node(0);
		tail = new Node(0);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Get the value of the index-th node in the linked list. If the index is
	 * invalid, return -1.
	 */
	public int get(int index) {
		if (index < 0 || index >= size) {
			return -1;
		}
		// choose the fastest way: to move from the head
		// or to move from the tail
		Node curr = head;
		if (index + 1 < size - index)
			for (int i = 0; i < index + 1; ++i)
				curr = curr.next;
		else {
			curr = tail;
			for (int i = 0; i < size - index; ++i)
				curr = curr.prev;
		}

		return curr.val;
	}

	/**
	 * Add a node of value val before the first element of the linked list. After
	 * the insertion, the new node will be the first node of the linked list.
	 */
	public void addAtHead(int val) {
		Node pred = head, succ = head.next;

		++size;
		Node toAdd = new Node(val);
		toAdd.prev = pred;
		toAdd.next = succ;
		pred.next = toAdd;
		succ.prev = toAdd;
	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {
		Node succ = tail, pred = tail.prev;

		++size;
		Node toAdd = new Node(val);
		toAdd.prev = pred;
		toAdd.next = succ;
		pred.next = toAdd;
		succ.prev = toAdd;
	}

	/**
	 * Add a node of value val before the index-th node in the linked list. If index
	 * equals to the length of linked list, the node will be appended to the end of
	 * linked list. If index is greater than the length, the node will not be
	 * inserted.
	 */
	public void addAtIndex(int index, int val) {
		if (index < 0 || index > size) {
			return;
		}
		Node pred, succ;
		// decide go from head or tail
		if (index < size - index) {
			pred = head;
			for (int i = 0; i < index; i++) {
				pred = pred.next;
			}
			succ = pred.next;

		} else {
			succ = tail;
			for (int i = 0; i < size - index; i++) {
				succ = succ.prev;
			}
			pred = succ.prev;
		}
		++size;
		Node newNode = new Node(val);

		pred.next = newNode;
		newNode.prev = pred;
		succ.prev = newNode;
		newNode.next = succ;
	}

	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index) {
		if (index < 0 || index >= size) {
			return;
		}
		Node pred, succ;
		// decide go from head or tail
		if (index < size - index) {
			pred = head;
			for (int i = 0; i < index; i++) {
				pred = pred.next;
			}
			succ = pred.next.next;
		} else {
			succ = tail;
			for (int i = 0; i < size - index - 1; i++) {
				succ = succ.prev;
			}
			pred = succ.prev.prev;
		}
		--size;
		pred.next = succ;
		succ.prev = pred;
	}

	class Node {
		Node prev;
		Node next;
		int val;

		public Node(int val) {
			this.val = val;
		}
	}
}