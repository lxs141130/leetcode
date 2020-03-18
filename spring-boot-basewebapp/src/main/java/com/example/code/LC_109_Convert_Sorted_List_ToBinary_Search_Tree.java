package com.example.code;

/**
 * @author franksun
 * 
 * Mar 11, 2020
 * 
 */
public class LC_109_Convert_Sorted_List_ToBinary_Search_Tree {
	
	public ListNode node;
	
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}
		node = head;
		ListNode temp = head;
		int len = 0;
		while (temp != null) {
			temp = temp.next;
			len++;
		}
		return helper(0, len - 1);
	}

	private TreeNode helper(int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNode left = helper(start, mid - 1);
		TreeNode root = new TreeNode(node.val);
		node = node.next;
		root.left = left;
		TreeNode right = helper(mid + 1, end);
		root.right = right;
		return root;
	}
	
	public static void main(String[] args) {
		LC_109_Convert_Sorted_List_ToBinary_Search_Tree st = new LC_109_Convert_Sorted_List_ToBinary_Search_Tree();
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		System.out.println(st.sortedListToBST(node1));
	}
}
