/**
 * 
 */
package indeed;

import java.util.LinkedList;
import java.util.List;

/**
 * @author franksun
 *
 */
public class LC_228_Summary_Ranges {
	public List<String> summaryRanges(int[] nums) {
		List<String> rst = new LinkedList<>();
		if (nums == null || nums.length == 0) {
			return rst;
		}

		int start = 0;
		for (int i = 0; i < nums.length; i++) {
			String s = "";
			if (i == nums.length - 1 || nums[i] + 1 != nums[i + 1]) {
				if (start != i) {
					s += nums[start] + "->" + nums[i];
				} else {
					s += nums[start];
				}
				start = i + 1;
				rst.add(s);
			}
		}
		return rst;
	}

	public List<String> summaryRanges1(int[] nums) {
		int length = nums.length;
		List<String> list = new LinkedList<>();
		int counter = 1;
		for (int i = 0; i < length; i++) {
			if (i < length - 1 && nums[i + 1] == nums[i] + 1) {
				counter++;
			} else {
				if (counter == 1) {
					list.add(String.valueOf(nums[i]));
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(nums[i - counter + 1]);
					sb.append("->");
					sb.append(nums[i]);
					list.add(sb.toString());
					counter = 1;
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		LC_228_Summary_Ranges sr = new LC_228_Summary_Ranges();
//    	int[] nums = {0, 1, 2, 4, 5, 7};
		int[] nums = { 0, 2, 3, 4, 6, 8, 9 };
		System.out.print(sr.summaryRanges(nums));
	}
}
