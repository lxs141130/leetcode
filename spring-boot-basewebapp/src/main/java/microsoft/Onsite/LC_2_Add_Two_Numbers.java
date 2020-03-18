package microsoft.Onsite;

import com.example.code.ListNode;

/**
 * @author franksun
 * 
 * Feb 21, 2020
 * 
 */
public class LC_2_Add_Two_Numbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		if (l1 == null || l2 == null) {
			return l1 != null ? l1 : l2;
		}
		int carry = 0;
		ListNode dummy = new ListNode(-1);
		ListNode newHead = dummy;
		while (l1 != null || l2 != null) {
			int sum = carry + (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
			l1 = l1.next != null ? l1.next : null;
			l2 = l2.next != null ? l2.next : null;
			ListNode node = new ListNode(sum % 10);
			newHead.next = node;
			newHead = newHead.next;
			carry = sum / 10;
		}
		if (carry != 0) {
			newHead.next = new ListNode(carry);
		}
		return dummy.next;
	}
}
