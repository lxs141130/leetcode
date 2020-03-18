package microsoft.Onsite;

import com.example.code.TreeNode;

/**
 * @author franksun
 * 
 * Feb 17, 2020
 * 
 */
public class LC_124_Binary_Tree_Maximum_Path_Sum {
	int max = Integer.MIN_VALUE;
	
	public int maxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		helper(root);
		return max;
	}

	/**
	 * @param root
	 * @return void
	 * @author franksun
	 */
	private int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = helper(root.left);
		int right = helper(root.right);
		max = Math.max(max, left + right + root.val);
		int ret = Math.max(left, right) + root.val;
		return ret > 0 ? ret : 0;
	}
	
	public static void main(String[] args) {
		LC_124_Binary_Tree_Maximum_Path_Sum mps = new LC_124_Binary_Tree_Maximum_Path_Sum();
		TreeNode root = new TreeNode(-10);
		TreeNode node1 = new TreeNode(9);
		TreeNode node2 = new TreeNode(20);
		TreeNode node3 = new TreeNode(15);
		TreeNode node4 = new TreeNode(7);
		root.left = node1;
		root.right = node2;
		node2.left = node3;
		node2.right = node4;
		System.out.print(mps.maxPathSum(root));
		
	}
}
