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
public class LC_94_Binary_Tree_Inorder_Traversal {
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    
    private void helper(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        helper(node.left, res);
        res.add(node.val);
        helper(node.right, res);
    }
    
    public List<Integer> inorderTraversal1(TreeNode root) {
    	 List<Integer> res = new ArrayList<>();
    	 if (root == null) {
             return res;
         }
    	 Stack<TreeNode> stack = new Stack<>();
    	 while(!stack.isEmpty() || root != null) {
    		 if(root != null) {
    			 stack.push(root);
    			 root = root.left;
    		 } else {
    			 TreeNode node = stack.pop();
    			 res.add(node.val);
    			 root = node.right;
    		 }
    	 }
    	 return res;
    }
}
