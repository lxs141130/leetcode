package amazonOA;

public class MergeTwoSortedLists {
	/**
	 * Merge two sorted linked lists and return it as a new list. The new list
	 * should be made by splicing together the nodes of the first two lists.
	 * 
	 * Example:
	 * 
	 * Input: 1->2->4, 1->3->4 Output: 1->1->2->3->4->4
	 */
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val > l2.val) {
				head.next = l2;
				l2 = l2.next;
			} else {
				head.next = l1;
				l1 = l1.next;
			}
			head = head.next;
		}
		if (l1 == null) {
			head.next = l2;
		}
		if (l2 == null) {
			head.next = l1;
		}
		return dummy.next;
	}
}