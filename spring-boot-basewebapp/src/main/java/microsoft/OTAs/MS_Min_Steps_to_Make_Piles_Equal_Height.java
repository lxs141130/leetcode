package microsoft.OTAs;

import java.util.Arrays;

/**
 * @author franksun
 * 
 *         Feb 11, 2020
 * 
 */

//Input: piles = [5, 2, 1]
//Output: 3
//Explanation:
//Step 1: reducing 5 -> 2 [2, 2, 1]
//Step 2: reducing 2 -> 1 [2, 1, 1]
//Step 3: reducing 2 -> 1 [1, 1, 1]
//So final number of steps required is 3.

//time -> O(N)
//space -> O(N)
public class MS_Min_Steps_to_Make_Piles_Equal_Height {
	public int minSteps(int[] piles) {
		if (piles == null || piles.length < 2) {
			return 0;
		}
		Arrays.sort(piles);
		int res = 0, distinctNums = 0;
		for (int i = 1; i < piles.length; ++i) {
			if (piles[i] != piles[i - 1]) {
				++distinctNums;
			}
			res += distinctNums;
		}
		return res;
	}

	public static void main(String[] args) {
		MS_Min_Steps_to_Make_Piles_Equal_Height ms = new MS_Min_Steps_to_Make_Piles_Equal_Height();
		int[] piles1 = { 5, 2, 1 };
		System.out.println(ms.minSteps(piles1));

		int[] piles2 = { 4, 5, 5, 4, 2 };
		System.out.println(ms.minSteps(piles2));

		int[] piles3 = { 1, 2, 5 };
		System.out.println(ms.minSteps(piles3));

		int[] piles4 = { 2, 2, 2 };
		System.out.println(ms.minSteps(piles4));

	}
}
