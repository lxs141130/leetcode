package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Mar 4, 2020
 * 
 */
public class LC_540_Single_Element_In_Sorted_Array {
	public int singleNonDuplicate(int[] nums) {
		int lo = 0;
		int hi = nums.length - 1;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			//不算mid,右边有偶数个ele
			boolean halvesAreEven = (hi - mid) % 2 == 0;
			if (nums[mid + 1] == nums[mid]) {
				if (halvesAreEven) {
					lo = mid + 2;
				} else {
					hi = mid - 1;
				}
			} else if (nums[mid - 1] == nums[mid]) {
				if (halvesAreEven) {
					hi = mid - 2;
				} else {
					lo = mid + 1;
				}
			} else {
				return nums[mid];
			}
		}
		return nums[lo];
	}
}
