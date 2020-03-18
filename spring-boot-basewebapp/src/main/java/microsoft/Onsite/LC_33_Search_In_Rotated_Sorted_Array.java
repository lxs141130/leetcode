package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Mar 2, 2020
 * 
 */
public class LC_33_Search_In_Rotated_Sorted_Array {
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (target == nums[mid]) {
				return mid;
			}

			if (nums[mid] < nums[right]) {
				if (target > nums[mid] && nums[right] >= target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				if (target < nums[mid] && nums[left] <= target) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}
		return -1;

	}

	public static void main(String[] args) {
		int[] nums = { 4, 5, 6, 7, 0, 1, 2, 3 };
		int target = 0;
		LC_33_Search_In_Rotated_Sorted_Array sr = new LC_33_Search_In_Rotated_Sorted_Array();
		System.out.println(sr.search(nums, target));
	}
}
