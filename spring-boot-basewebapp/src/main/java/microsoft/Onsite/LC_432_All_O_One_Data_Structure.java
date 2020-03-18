package microsoft.Onsite;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 *         Mar 4, 2020
 * 
 */
public class LC_432_All_O_One_Data_Structure {
	Node head = new Node(""); // max side
	Node tail = new Node(""); // min side
	Map<String, Node> map = new HashMap<>();

	/** Initialize your data structure here. */
	public LC_432_All_O_One_Data_Structure() {
		head.next = tail;
		tail.prev = head;
	}
//head-A-B-C-tail

//A 2
//B 2
//C 1
	// D - 1

	/** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
	public void inc(String key) {
		Node current = map.computeIfAbsent(key, k -> new Node(key));
		current.count++;
		if (current.count != 1) {
			removeNodeFromList(current);
		}
		insertNodeToList(current);
	}

	/**
	 * Decrements an existing key by 1. If Key's value is 1, remove it from the data
	 * structure.
	 */
	public void dec(String key) {
		Node current = map.getOrDefault(key, new Node(key));
		if (current.count == 0) {
			return;
		}
		current.count--;
		removeNodeFromList(current);
		if (current.count == 0) {
			map.remove(key);
		} else {
			insertNodeToList(current);
		}
	}

	/** Returns one of the keys with maximal value. */
	public String getMaxKey() {
		return head.next.key;
	}

	/** Returns one of the keys with Minimal value. */
	public String getMinKey() {
		return tail.prev.key;
	}

	private void insertNodeToList(Node current) {
		if (current.count > head.next.count) { // it handles the 0 size list as well;
			head.next.prev = current;
			current.next = head.next;
			head.next = current;
			current.prev = head;
		} else if (current.count < tail.prev.count) {
			tail.prev.next = current;
			current.prev = tail.prev;
			tail.prev = current;
			current.next = tail;
		} else {
			Node prev = head.next;
			Node next = head.next.next;
			prev.next = current;
			current.prev = prev;
			current.next = next;
			next.prev = current;
		}
	}

	private void removeNodeFromList(Node current) {
		if (head.next == tail) {
			return;
		}
		Node prev = current.prev;
		Node next = current.next;
		prev.next = next;
		next.prev = prev;
		current.next = null;
		current.prev = null;
	}

	class Node {
		Node prev;
		Node next;
		String key;
		int count = 0;

		public Node(String key) {
			this.key = key;
		}
	}
}
