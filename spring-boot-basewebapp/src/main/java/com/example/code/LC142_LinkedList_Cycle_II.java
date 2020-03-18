package com.example.code;

public class LC142_LinkedList_Cycle_II {
	public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null) {
            return null;
        }
        slow = head;
        fast = fast.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
	
	public static void main(String[] args) {
		LC142_LinkedList_Cycle_II lc2 = new LC142_LinkedList_Cycle_II();
		ListNode head = new ListNode(3);
		ListNode h1 = new ListNode(2);
		ListNode h2 = new ListNode(0);
		ListNode h3 = new ListNode(-4);
		ListNode h4 = new ListNode(-5);
		head.next = h1;
		h1.next = h2;
		h2.next = h3;
		h3.next = h4;
		h4.next = h2;
		System.out.print(lc2.detectCycle(head));
	}
}	
