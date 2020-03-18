package com.example.learning;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LC146_LRU_Cache extends LinkedHashMap<Integer, Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int capacity;

	public LC146_LRU_Cache(int capacity) {
		super(capacity, 0.75F, true);
		this.capacity = capacity;
	}

	public int get(int key) {
		return super.getOrDefault(key, -1);
	}

	public void put(int key, int value) {
		super.put(key, value);
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
		return size() > capacity;
	}
}

class LRUCache {

	class DLinkedNode {
		int key;
		int value;
		DLinkedNode prev;
		DLinkedNode next;
		
		public DLinkedNode() {}
		
		public DLinkedNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private void addNode(DLinkedNode node) {
		/**
		 * Always add the new node right after head.
		 */
		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;
	}

	private void removeNode(DLinkedNode node) {
		/**
		 * Remove an existing node from the linked list.
		 */
		DLinkedNode prev = node.prev;
		DLinkedNode next = node.next;

		prev.next = next;
		next.prev = prev;
	}

	private void moveToHead(DLinkedNode node) {
		/**
		 * Move certain node in between to the head.
		 */
		removeNode(node);
		addNode(node);
	}

	private DLinkedNode popTail() {
		/**
		 * Pop the current tail.
		 */
		DLinkedNode res = tail.prev;
		removeNode(res);
		return res;
	}

	private Map<Integer, DLinkedNode> cache = new HashMap<>();
	private int size;
	private int capacity;
	private DLinkedNode head, tail;

	public LRUCache(int capacity) {
		this.size = 0;
		this.capacity = capacity;

		head = new DLinkedNode();
		tail = new DLinkedNode();

		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		DLinkedNode node = cache.get(key);
		if (node == null)
			return -1;

		// move the accessed node to the head;
		moveToHead(node);

		return node.value;
	}

	public void put(int key, int value) {
		DLinkedNode node = cache.get(key);

		if (node == null) {
			DLinkedNode newNode = new DLinkedNode(key, value);

			cache.put(key, newNode);
			addNode(newNode);

			++size;

			if (size > capacity) {
				// pop the tail
				DLinkedNode tail = popTail();
				cache.remove(tail.key);
				--size;
			}
		} else {
			// update the value.
			node.value = value;
			moveToHead(node);
		}
	}
}