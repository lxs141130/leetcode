package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Mar 4, 2020
 * 
 */
public class LC_153_Find_Minimum_In_Rotated_Sorted_Array {
	public int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int low = 0;
		int high = nums.length - 1;
		while (low < high && nums[low] > nums[high]) {
			int mid = (low + high) / 2;
			// nums[low]总是比nums[high]大的，因为要找min，所以和nums[high]做比较
			if (nums[mid] > nums[high]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return nums[low];
	}
}
