package indeed;

import java.util.HashSet;
import java.util.Set;

/**
 * @author franksun
 * 
 *         Feb 10, 2020
 * 
 */
public class LC_349_Intersection_of_Two_Arrays {
	public int[] intersection(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null) {
			return new int[0];
		}
		Set<Integer> set = new HashSet<>();
		Set<Integer> rstSet = new HashSet<>();
		for (int i = 0; i < nums1.length; i++) {
			set.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			if (set.contains(nums2[i])) {
				rstSet.add(nums2[i]);
			}
		}
		int[] rst = new int[rstSet.size()];
		int i = 0;
		for (int num : rstSet) {
			rst[i++] = num;
		}
		return rst;
	}
}
