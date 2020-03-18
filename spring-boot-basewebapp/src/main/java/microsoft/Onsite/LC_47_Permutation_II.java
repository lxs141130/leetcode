package microsoft.Onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author franksun
 * 
 *         Mar 5, 2020
 * 
 */
public class LC_47_Permutation_II {
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		boolean[] visited = new boolean[nums.length];
		Arrays.sort(nums);// 存在重复元素，所以先排序
		dfs(res, nums, visited, new ArrayList<>());
		return res;
	}

	private void dfs(List<List<Integer>> res, int[] nums, boolean[] visited, List<Integer> temp) {
		if (temp.size() == nums.length) {
			res.add(new ArrayList<>(temp));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
				continue;
			}

			if (!visited[i]) {
				visited[i] = true;
				temp.add(nums[i]);
				dfs(res, nums, visited, temp);
				temp.remove(temp.size() - 1);
				visited[i] = false;
			}
		}
	}
}
