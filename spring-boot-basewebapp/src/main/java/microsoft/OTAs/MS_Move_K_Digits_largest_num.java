package microsoft.OTAs;

import java.util.LinkedList;

/**
 * @author franksun
 * 
 *         Feb 14, 2020
 * 
 */

//Time complexity : O(N)
//Space complexity : O(N)
public class MS_Move_K_Digits_largest_num {
	public static String removeKdigits(String num, int k) {
		if (num.length() <= k) {
			return "0";
		}
		//stack to store char, largest char will be moved to last
		LinkedList<Character> stack = new LinkedList<Character>();

		for (char digit : num.toCharArray()) {
			while (stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
				stack.removeLast();
				k -= 1;
			}
			//current digit is the largest one, we add it to the end
			stack.addLast(digit);
		}

		//remove the remaining digits from the tail as we put the largest char at end
		for (int i = 0; i < k; ++i) {
			stack.removeLast();
		}

		// build the final answer
		StringBuilder rst = new StringBuilder();
		for (char digit : stack) {
			rst.append(digit);
		}
		//remove leading zero
		int i = 0;
		while (i < rst.length() && rst.charAt(i) == '0') {
			rst.deleteCharAt(i);
		}

		return rst.length() == 0 ? "0" : rst.toString();
	}

	public static void main(String[] args) {
		// Input: num = "1432219", k = 3
		// Output: "1219"
		System.out.println(removeKdigits("1432219", 3));
		// Input: num = "10200", k = 1
		// Output: "200"
		System.out.println(removeKdigits("10200", 1));
		// Input: num = "10", k = 2
		// Output: "0"
		System.out.println(removeKdigits("10", 2));
	}
}
