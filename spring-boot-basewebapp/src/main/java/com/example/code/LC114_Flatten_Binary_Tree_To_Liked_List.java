package com.example.code;

import java.util.LinkedList;
import java.util.List;

public class LC114_Flatten_Binary_Tree_To_Liked_List {
	public void flatten(TreeNode root) {
        List<TreeNode> res = helper(root);
        TreeNode head = root;
        for (TreeNode node: res) {
            if (node != root) {
                head.right = node;
                head.left = null;
                head = head.right;
            }
        }
        
    }
    
    private List<TreeNode> helper(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> left = helper(root.left);
        List<TreeNode> right = helper(root.right);
        res.add(root);
        res.addAll(left);
        res.addAll(right);
        return res;
    }
    
    public static void main(String[] args) {
    	LC114_Flatten_Binary_Tree_To_Liked_List wl = new LC114_Flatten_Binary_Tree_To_Liked_List();
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(5);
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(4);
		TreeNode node6 = new TreeNode(6);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.right = node6;
		
		wl.flatten(node1);
		TreeNode node = node1;
		
		while(node!=null) {
			System.out.print(node.val + " -> ");
			node = node.right;
		}
	}
}

