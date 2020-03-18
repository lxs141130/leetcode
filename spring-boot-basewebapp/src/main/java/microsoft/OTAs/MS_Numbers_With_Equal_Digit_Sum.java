package microsoft.OTAs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 * Feb 11, 2020
 * 
 */
//https://leetcode.com/discuss/interview-question/365872/

//https://www.lintcode.com/problem/maximum-sum-of-two-numbers/description

public class MS_Numbers_With_Equal_Digit_Sum {
    //time complexity -> O(N) * O(len(A[i])) -> O(N) N is the size of A
    //space complexity -> O(N) -> N is the size of A, worst case is no valid pairs, we need to store everything inside map
	public int MaximumSum (int[] A) {
	    //corner case for invalid input
		if (A == null || A.length < 2) {
			return -1;
		}
		//default invalid max
		int max = Integer.MIN_VALUE;
		//key -> digitSum
		//value -> num in A
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : A) {
			int digitSum = getDigitSum(num);
			//if any number already check has the same digitSum with current num
			if (map.containsKey(digitSum)) {
			    //check if sum of those two numbers are larger than cumulative max
				max = Math.max(max, num + map.get(digitSum));
				//update map with larger number whose has the same digit sum
				map.put(digitSum, Math.max(num, map.get(digitSum)));
			} else {
			    //no match, just insert entry
				map.put(digitSum, num);
			}
		}
		//if no valid entry existed, return -1, else return cumulative max
		return max == Integer.MIN_VALUE ? -1 : max;
	}
	
	//method to get digit sum of a given int
	private int getDigitSum (int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		MS_Numbers_With_Equal_Digit_Sum nds = new MS_Numbers_With_Equal_Digit_Sum();
		int[] A = {51,71,17,42, 60};
		System.out.println(nds.MaximumSum(A));
		
		int[] A1 = {42,33,60};
		System.out.println(nds.MaximumSum(A1));
		
		int[] A2 = {51,32,43};
		System.out.println(nds.MaximumSum(A2));
	}
}
