package com.example.learning;

import java.util.HashMap;
import java.util.Map;

public class LC460_LFU_Cache {
	int capacity, size, min;
	Map<Integer, Node> nodeMap;
	Map<Integer, DLList> countMap;

	public LC460_LFU_Cache(int capacity) {
		this.capacity = capacity;
		nodeMap = new HashMap<>();
		countMap = new HashMap<>();
	}

	public int get(int key) {
		Node node = nodeMap.get(key);
		if (node == null) {
			return -1;
		}
		update(node);
		return node.val;
	}

	public void put(int key, int value) {
		if (capacity == 0) {
			return;
		}
		Node node;
		if (nodeMap.containsKey(key)) {
			node = nodeMap.get(key);
			node.val = value;
			update(node);
		} else {
			node = new Node(key, value);
			nodeMap.put(key, node);
			if (size == capacity) {
				DLList lastList = countMap.get(min);
				nodeMap.remove(lastList.removeLast().key);
				size--;
			}
			size++;
			min = 1;
			DLList newList = countMap.getOrDefault(node.cnt, new DLList());
			newList.add(node);
			countMap.put(node.cnt, newList);
		}
	}

	private void update(Node node) {
		DLList oldList = countMap.get(node.cnt);
		oldList.remove(node);
		if (node.cnt == min && oldList.size == 0)
			min++;
		node.cnt++;
		DLList newList = countMap.getOrDefault(node.cnt, new DLList());
		newList.add(node);
		countMap.put(node.cnt, newList);
	}
	
	public static void main(String[] args) {
		LC460_LFU_Cache cache = new LC460_LFU_Cache(2);
	
		cache.put(1, 1);
		cache.put(2, 2);
		cache.get(1);       // returns 1
		cache.put(3, 3);    // evicts key 2
		cache.get(2);       // returns -1 (not found)
		cache.get(3);       // returns 3.
		cache.put(4, 4);    // evicts key 1.
		cache.get(1);       // returns -1 (not found)
		cache.get(3);       // returns 3
		cache.get(4);       // returns 4
		
	}
}

class Node {
	int key, val, cnt;
	Node prev, next;

	Node(int key, int val) {
		this.key = key;
		this.val = val;
		cnt = 1;
	}
}

class DLList {
	Node head, tail;
	int size;

	public DLList() {
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	public void add(Node node) {
		head.next.prev = node;
		node.next = head.next;
		node.prev = head;
		head.next = node;
		size++;
	}

	public void remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		size--;
	}

	public Node removeLast() {
		if (size > 0) {
			Node node = tail.prev;
			remove(node);
			return node;
		} else
			return null;
	}
}