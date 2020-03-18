package microsoft.Onsite;

import java.util.Arrays;
import java.util.List;

/**
 * @author franksun
 * 
 *         Mar 3, 2020
 * 
 */
public class LC_315_Count_Of_Smaller_Numbers_After_Self {
	public List<Integer> countSmaller(int[] nums) {
		Integer[] ans = new Integer[nums.length];
		Node root = null;

		for (int i = nums.length - 1; i >= 0; i--) {
			root = insert(nums[i], root, ans, i, 0);
		}
		return Arrays.asList(ans);
	}

	private Node insert(int num, Node node, Integer[] ans, int index, int preSum) {
		if (node == null) {
			// any new node has no left bot node, then sum will be 0
			node = new Node(num, 0);
			ans[index] = preSum;
		} else if (node.val == num) {
			node.times++;
			ans[index] = preSum + node.sum;
		} else if (node.val > num) {
			// one more left bot
			node.sum++;
			node.left = insert(num, node.left, ans, index, preSum);
		} else {
			node.right = insert(num, node.right, ans, index, node.sum + node.times + preSum);
		}
		return node;
	}

	class Node {
		int times = 1;// how many times the node appear
		int sum;// how many node to its left bottom
		int val;// node val
		Node left;
		Node right;

		public Node(int val, int sum) {
			this.val = val;
			this.sum = sum;
		}
	}

	public static void main(String[] args) {
		LC_315_Count_Of_Smaller_Numbers_After_Self cos = new LC_315_Count_Of_Smaller_Numbers_After_Self();
		System.out.println(cos.countSmaller(new int[] { 5, 2, 6, 1 }));
	}
}
