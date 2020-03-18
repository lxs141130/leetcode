package microsoft.Onsite;

/**
 * @author franksun
 * 
 * Mar 4, 2020
 * 
 */
public class LC_154_FInd_Minimum_IN_Rotated_Sorted_Array_II {
	public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                high--;       
            }
        }
        return nums[low];
    }
}
