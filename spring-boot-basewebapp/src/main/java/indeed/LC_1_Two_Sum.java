package indeed;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 *         Feb 10, 2020
 * 
 */
public class LC_1_Two_Sum {
	// if sorted
	public int[] twoSum1(int[] nums, int target) {
		int[] rst = new int[] { -1, -1 };
		if (nums == null || nums.length == 0) {
			return rst;
		}
		int i = 0;
		int j = nums.length - 1;
		while (i < j) {
			int sum = nums[i] + nums[j];
			if (sum == target) {
				rst[0] = i;
				rst[1] = j;
				break;
			} else if (sum > target) {
				j--;
			} else {
				j++;
			}
		}
		return rst;
	}

	// if not sort
	public int[] twoSum2(int[] nums, int target) {
		int[] rst = new int[] { -1, -1 };
		if (nums == null || nums.length == 0) {
			return rst;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				rst[0] = i;
				rst[1] = map.get(target - nums[i]);
				break;
			} else {
				map.put(nums[i], i);
			}
		}
		return rst;
	}
}
