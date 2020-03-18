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
public class LC_145_Binary_Tree_Post_Order_Traversal {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		helper(res, root);
		return res;
	}

	private void helper(List<Integer> res, TreeNode root) {
		if (root == null) {
			return;
		}
		helper(res, root.left);
		helper(res, root.right);
		res.add(root.val);
	}
	
	public List<Integer> postorderTraversal1(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode top = stack.peek();
			if (top.left == null && top.right == null) {
				stack.pop();
				res.add(top.val);
			}
			if (top.right != null) {
				stack.push(top.right);
				top.right = null;
			}
			if(top.left != null) {
				stack.push(top.left);
				top.left = null;
			}
		}
		return res;
	}
}
