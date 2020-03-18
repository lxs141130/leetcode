package evernote;

/**
 * @author franksun
 * 
 *         Mar 9, 2020
 * 
 */
public class LC_53_Maximum_SubArray {
	public int maxSubArray(int[] nums) {
		int max = Integer.MIN_VALUE;
		if (nums == null || nums.length == 0) {
			return max;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int temp = nums[0];
		max = temp;
		for (int i = 1; i < nums.length; i++) {
			temp = Math.max(temp + nums[i], nums[i]);
			max = Math.max(max, temp);
		}
		return max;
	}

	public int maxSubArray1(int[] nums) {
		return helper(nums, 0, nums.length - 1);
	}

	public int helper(int[] nums, int left, int right) {
		if (left == right) {
			return nums[left];
		}
		int p = left + (right - left) / 2;

		int leftSum = helper(nums, left, p);
		int rightSum = helper(nums, p + 1, right);
		int crossSum = crossSum(nums, left, right, p);

		return Math.max(Math.max(leftSum, rightSum), crossSum);
	}

	private int crossSum(int[] nums, int left, int right, int p) {
		if (left == right) {
			return nums[left];
		}

		int leftSubsum = Integer.MIN_VALUE;
		int currSum = 0;

		for (int i = p; i > left - 1; --i) {
			currSum += nums[i];
			leftSubsum = Math.max(leftSubsum, currSum);
		}

		int rightSubsum = Integer.MIN_VALUE;
		currSum = 0;
		for (int i = p + 1; i < right + 1; ++i) {
			currSum += nums[i];
			rightSubsum = Math.max(rightSubsum, currSum);
		}
		return leftSubsum + rightSubsum;
	}
}
