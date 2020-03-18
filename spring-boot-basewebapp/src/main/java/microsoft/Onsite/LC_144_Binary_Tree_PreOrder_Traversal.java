package microsoft.Onsite;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.example.code.TreeNode;

/**
 * @author franksun
 * 
 * Mar 5, 2020
 * 
 */
public class LC_144_Binary_Tree_PreOrder_Traversal {
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		helper(res, root);
		return res;
	}

	private void helper(List<Integer> res, TreeNode root) {
		if (root == null) {
			return;
		}
		res.add(root.val);
		helper(res, root.left);
		helper(res, root.right);
	}
	
	
	public List<Integer> preorderTraversal1(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			res.add(node.val);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		return res;
	}
}
