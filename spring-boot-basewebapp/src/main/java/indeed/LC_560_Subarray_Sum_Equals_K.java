package indeed;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 *         Feb 10, 2020
 * 
 */
public class LC_560_Subarray_Sum_Equals_K {
	public int subarraySum(int[] nums, int k) {
		int ans = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		// sum different equals 0 for getting start
		map.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			// check if there is any point exist
			if (map.containsKey(sum - k)) {
				ans += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return ans;
	}

	public static void main(String[] args) {
		LC_560_Subarray_Sum_Equals_K ss = new LC_560_Subarray_Sum_Equals_K();
		int[] nums = { 1, 1, 1, 1 };
		int k = 3;
		System.out.println(ss.subarraySum(nums, k));
	}
}
