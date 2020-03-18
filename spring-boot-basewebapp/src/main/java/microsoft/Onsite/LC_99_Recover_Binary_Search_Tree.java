package microsoft.Onsite;

import java.util.ArrayList;
import java.util.List;

import com.example.code.TreeNode;

/**
 * @author franksun
 * 
 *         Mar 5, 2020
 * 
 */
public class LC_99_Recover_Binary_Search_Tree {

	TreeNode first = null;
	TreeNode second = null;
	TreeNode pre = new TreeNode(Integer.MIN_VALUE);

	public void recoverTree(TreeNode root) {
		inorderReverse(root);// left-curr-right 如果出现递减就说明出错
		// 交换
		int tmp = first.val;
		first.val = second.val;
		second.val = tmp;
	}

	public void inorderReverse(TreeNode root) {
		if (root == null)
			return;

		inorderReverse(root.left);
		// 出现第一次递减，记录第一个值 3—1 纪录5
		if (first == null && pre.val >= root.val) {
			first = pre;
		}
		// 出现第二次，记录第二个值 6-2 记录2
		if (first != null && pre.val >= root.val) {
			second = root;
		}

		pre = root;
		inorderReverse(root.right);
	}

	public void recoverTree1(TreeNode root) {
		List<Integer> nums = new ArrayList<>();
		inorder(root, nums);
		int[] swapped = findTwoSwapped(nums);
		recover(root, 2, swapped[0], swapped[1]);
	}

	public void inorder(TreeNode root, List<Integer> nums) {
		if (root == null) {
			return;
		}
		inorder(root.left, nums);
		nums.add(root.val);
		inorder(root.right, nums);
	}

	public int[] findTwoSwapped(List<Integer> nums) {
		int n = nums.size();
		int x = -1, y = -1;
		for (int i = 0; i < n - 1; ++i) {
			if (nums.get(i + 1) < nums.get(i)) {
				y = nums.get(i + 1);
				// first swap occurence
				if (x == -1) {
					x = nums.get(i);
				}
				// second swap occurence
				else {
					break;
				}
			}
		}
		return new int[] { x, y };
	}

	public void recover(TreeNode r, int count, int x, int y) {
		if (r != null) {
			if (r.val == x || r.val == y) {
				r.val = r.val == x ? y : x;
				if (--count == 0) {
					return;
				}
			}
			recover(r.left, count, x, y);
			recover(r.right, count, x, y);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		root.left = node2;
		node2.right = node1;

		LC_99_Recover_Binary_Search_Tree rb = new LC_99_Recover_Binary_Search_Tree();
		rb.recoverTree(root);
	}
}
