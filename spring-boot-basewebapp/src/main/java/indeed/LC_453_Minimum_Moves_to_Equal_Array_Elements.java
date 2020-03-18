package indeed;

/**
 * @author franksun
 * 
 *         Feb 10, 2020
 * 
 */

//adding 1 to all the elements except one is equivalent to 
// decrementing 1 from a single element, 
//since we are interested in the relative levels of the elements 
//which need to be equalized. 
//Thus, 
//the problem is simplified to find the number of decrement operations required to 
//equalize all the elements of the given array. 
//For finding this, it is obvious that we'll reduce all the elements of the array to the minimum element.
public class LC_453_Minimum_Moves_to_Equal_Array_Elements {
	public int minMoves(int[] nums) {
		int min = Integer.MAX_VALUE;
		int rst = 0;
		for (int num : nums) {
			rst += num;
			min = Math.min(min, num);
		}
		return rst - min * nums.length;
	}
}
