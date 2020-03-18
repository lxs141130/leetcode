package indeed;

/**
 * @author franksun
 * 
 *         Feb 10, 2020
 * 
 */
public class LC_88_Merge_Sorted_Array {

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m;
		int j = n;
		while (i > 0 && j > 0) {
			if (nums1[i - 1] > nums2[j - 1]) {
				nums1[i + j - 1] = nums1[i - 1];
				i--;
			} else {
				nums1[i + j - 1] = nums2[j - 1];
				j--;
			}
		}
		while (j != 0) {
			nums1[i + j - 1] = nums2[j - 1];
			j--;
		}

	}

	public static void main(String[] args) {
		LC_88_Merge_Sorted_Array ms = new LC_88_Merge_Sorted_Array();
		ms.merge(new int[] { 1, 2, 3, 0, 0, 0 }, 3, new int[] { 2, 5, 6 }, 3);
	}
}
