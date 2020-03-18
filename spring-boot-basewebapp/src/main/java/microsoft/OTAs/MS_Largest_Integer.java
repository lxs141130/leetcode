package microsoft.OTAs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 * Feb 12, 2020
 * 
 */

//https://leetcode.com/discuss/interview-question/406031/


public class MS_Largest_Integer {
	public static void main(String[] args) {
		int[] nums1 = { 3, 2, -2, 5, -3 };
		int[] nums2 = { 1, 2, 3, -4 };
		int[] nums3 = { 1, 2, 3};
		int[] nums4 = { -3, -4 ,3, -3, -3};
		System.out.println(largestNum(nums1));
		System.out.println(largestNum(nums2));
		System.out.println(largestNum(nums3));
		System.out.println(largestNum(nums4));
		System.out.println("-------------------------------------");
		System.out.println(largestNum2(nums1));
		System.out.println(largestNum2(nums2));
		System.out.println("-------------------------------------");
		System.out.println(largestNum3(nums1));
		System.out.println(largestNum3(nums2));
	}

	//time -> O(N)
	//space -> O(N)
	private static int largestNum(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<>();
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i] * -1)) {
				max = Math.max(max, Math.abs(nums[i]));
			}
			map.put(nums[i], i);
		}
		return max;
	}
	
	//time -> O(N^2)
	//space -> O(1)
	private static int largestNum2(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int max = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int sum = nums[i] + nums[j];
				if (sum == 0) {
					max = Math.max(max, Math.abs(nums[i]));
				}
			}
		}
		return max;
	}
	
	//time -> O(nlogn)
	//space -> O(1)
	private static int largestNum3(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int max = 0;
		int left = 0;
		int right = nums.length -1;
		while (left < right) {
			int sum = nums[left] + nums[right]; 
			if (sum == 0) {
				max = Math.max(max, nums[right]);
				left++;
				right--;
			} else if (sum < 0) {
				left++;
			} else {
				right--;
			}
		}
		return max;
	}
}
