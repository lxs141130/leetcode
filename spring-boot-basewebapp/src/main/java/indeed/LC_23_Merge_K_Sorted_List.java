package indeed;

import com.example.code.ListNode;

/**
 * @author franksun
 * 
 * Feb 8, 2020
 * 
 */
public class LC_23_Merge_K_Sorted_List {
	
	public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        while(lists.length > 1) {
            ListNode[] newList = new ListNode[(lists.length + 1) / 2];
            int index = 0;
            for (int i = 0; i < lists.length - 1; i += 2) {
                newList[index++] = mergeTwoList(lists[i], lists[i + 1]);
            }
            if (lists.length % 2 == 1) {
                newList[index] = lists[lists.length - 1];
            }
            lists = newList;
        }
        return lists[0];
    }
	
	private ListNode mergeTwoList (ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode node = dummy;
		
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				node.next = l1;
				l1 = l1.next;
			} else {
				node.next = l2;
				l2 = l2.next;
			}
			node = node.next;
		}
		if (l1  != null) {
			node.next = l1;
		}
		if (l2 != null) {
			node.next = l2;
		}
		return dummy.next;
	}
	
	public static void main(String[] args) {
		ListNode[] lists = new ListNode[3];
		ListNode list1 = new ListNode(1);
		ListNode node1 = new ListNode(4);
		ListNode node2 = new ListNode(5);
		list1.next = node1;
		node1.next = node2;
		
		ListNode list2 = new ListNode(1);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		list2.next = node3;
		node3.next = node4;
		
		ListNode list3 = new ListNode(2);
		ListNode node5 = new ListNode(6);
		list3.next = node5;
		
		lists[0] = list1;
		lists[1] = list2;
		lists[2] = list3;
		
		LC_23_Merge_K_Sorted_List mks = new LC_23_Merge_K_Sorted_List();
		
		System.out.println(mks.mergeKLists(lists));
	}
}
