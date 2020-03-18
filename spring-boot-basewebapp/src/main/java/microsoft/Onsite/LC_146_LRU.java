package microsoft.Onsite;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 *         Feb 17, 2020
 * 
 */

class LC_146_LRU {
	
	DLinkedNode head;
	DLinkedNode tail;
	int capacity;
	Map<Integer, DLinkedNode> cache;

	public LC_146_LRU(int capacity) {
		this.capacity = capacity;
		cache = new HashMap<>();
		
		head = new DLinkedNode();
		tail = new DLinkedNode();
		head.next = tail;
		tail.prev = head;
		
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			DLinkedNode node = cache.get(key);	
			moveToHead(node);
			return node.val;
		} else {
			return -1;
		}
	}

	/**
	 * @param node
	 * @return void
	 * @author franksun
	 */
	private void moveToHead(DLinkedNode node) {
		// TODO Auto-generated method stub
		remove(node);
	    add(node);
	}

	/**
	 * @param node
	 * @return void
	 * @author franksun
	 */
	private void add(DLinkedNode node) {
		head.next.prev = node;
		node.next = head.next;
		head.next = node;
		node.prev = head;		
		
	}
	
	/**
	 * @param node
	 * @return void
	 * @author franksun
	 */
	private void remove(DLinkedNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	public void put(int key, int value) {
		if (cache.containsKey(key)) {
			DLinkedNode node = cache.get(key);
			node.val = value;
			moveToHead(node);
		} else {
			DLinkedNode newNode = new DLinkedNode(key, value);
			if (capacity == cache.size()) {
				cache.remove(tail.prev.key);
				remove(tail.prev);
			}
			cache.put(key, newNode);
			add(newNode);
		}
	}
	
	public static void main(String[] args) {
		LC_146_LRU cache = new LC_146_LRU( 2 /* capacity */ );

		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    // evicts key 2
		System.out.println(cache.get(2));       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
	}
}

class DLinkedNode {
	int key;
	int val;
	DLinkedNode prev;
	DLinkedNode next;
	
	public DLinkedNode() {}
	
	public DLinkedNode(int key, int val) {
		this.key = key;
		this.val = val;
	}
	
}

/**
 * Your LRUCache object will be instantiated and called as such: 
 * LRUCache obj = new LRUCache(capacity); 
 * int param_1 = obj.get(key); 
 * obj.put(key,value);
 */