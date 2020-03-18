package microsoft.Onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franksun
 * 
 *         Mar 5, 2020
 * 
 */
public class LC_46_Permutations {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> rst = new ArrayList<>();
		helper(rst, nums, new ArrayList<Integer>());
		return rst;
	}

	private void helper(List<List<Integer>> rst, int[] nums, List<Integer> temp) {
		if (temp.size() == nums.length) {
			rst.add(new ArrayList<Integer>(temp));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (!temp.contains(nums[i])) {
					temp.add(nums[i]);
					helper(rst, nums, temp);
					temp.remove(temp.size() - 1);
				}
			}
		}
	}
}
