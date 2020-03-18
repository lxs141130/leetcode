package microsoft.Onsite;

import com.example.code.ListNode;

/**
 * @author franksun
 * 
 * Feb 20, 2020
 * 
 */
public class LC_234_Palindrome_Linked_List {
	public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = findMiddle(head);
        ListNode newHead = reverseList(mid.next);
        mid.next = null;
        
        while (head != null && newHead != null) {
            if (head.val != newHead.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }
        
        return true;
    }
    
    private ListNode findMiddle(ListNode node){
        if (node == null || node.next == null) {
            return node;
        }
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    //2->1
    //1->2->null
    private ListNode reverseList(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode pre = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
