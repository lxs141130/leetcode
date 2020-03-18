package microsoft.Onsite;

import com.example.code.ListNode;

/**
 * @author franksun
 * 
 *         Mar 5, 2020
 * 
 */
public class LC_25_Reverse_Linked_List_In_K_Group {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode curr = head;
		int count = 0;
		while (curr != null && count != k) { // find the k+1 node
			curr = curr.next;
			count++;
		} // 不是完整的k不用反转
		if (count == k) {
			// 后面整理好的的list
			curr = reverseKGroup(curr, k);
			// 反转这一步部分
			while (count > 0) {
				ListNode next = head.next;
				head.next = curr;// 连好head->curr
				// next->head->curr
				curr = head;
				head = next;
				count--;
			}
			// 回到整理好的开始
			head = curr;
		}
		return head;
	}

}
