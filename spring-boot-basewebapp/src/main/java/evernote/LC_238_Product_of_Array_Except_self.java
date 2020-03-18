package evernote;

/**
 * @author franksun
 * 
 *         Mar 8, 2020
 * 
 */
public class LC_238_Product_of_Array_Except_self {

	public int[] productExceptSelf(int[] nums) {
		int[] rst = new int[nums.length];
		// left [1,1,2,6]
		int[] left = new int[nums.length];
		left[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			left[i] = nums[i - 1] * left[i - 1];
		}

		// right [24,12,4,1]
		int[] right = new int[nums.length];
		right[nums.length - 1] = 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			right[i] = right[i + 1] * nums[i + 1];
		}

		// rst [24, 12, 8, 6]
		for (int i = 0; i < nums.length; i++) {
			rst[i] = left[i] * right[i];
		}
		return rst;
	}

	public int[] productExceptSelf1(int[] nums) {
		int[] rst = new int[nums.length];
		rst[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			rst[i] = nums[i - 1] * rst[i - 1];
		}
		// left
		// [1,1,2,6]

		// nums
		// [1,2,3,4]

		// rst
		// [24,12,8,6]

		int right = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			rst[i] = rst[i] * right;
			right *= nums[i];
		}
		return rst;
	}

	public static void main(String[] args) {
		LC_238_Product_of_Array_Except_self ps = new LC_238_Product_of_Array_Except_self();
		System.out.println(ps.productExceptSelf(new int[] { 1, 2, 3, 4 }));
	}
}
