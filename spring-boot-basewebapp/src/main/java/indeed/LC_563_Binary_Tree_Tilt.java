package indeed;

import com.example.code.TreeNode;

/**
 * @author franksun
 * 
 * Feb 9, 2020
 * 
 */
public class LC_563_Binary_Tree_Tilt {
	int tilt = 0;
    public int findTilt(TreeNode root) {
        helper(root);
        return tilt;
    }
    
    public int helper (TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = helper(node.left);
        int right = helper(node.right);
        
        tilt += Math.abs(left - right);
        
        return left + right + node.val;
    }
}
