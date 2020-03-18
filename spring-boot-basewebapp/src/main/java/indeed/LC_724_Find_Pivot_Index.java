package indeed;

/**
 * @author franksun
 * 
 *         Feb 10, 2020
 * 
 */
public class LC_724_Find_Pivot_Index {
	public int pivotIndex(int[] nums) {
		int sum = 0;
		int leftSum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		// leftSum is the sum before index i
		// right sum should be sum - leftSum- nums[i]
		for (int i = 0; i < nums.length; i++) {
			if (leftSum == sum - leftSum - nums[i]) {
				return i;
			}
			leftSum += nums[i];
		}

		return -1;
	}

	public static void main(String[] args) {
		LC_724_Find_Pivot_Index fpi = new LC_724_Find_Pivot_Index();
		int[] nums = { 1, 7, 3, 6, 5, 6 };
		System.out.println(fpi.pivotIndex(nums));
	}
}
