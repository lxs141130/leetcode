package microsoft.Onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franksun
 * 
 *         Mar 3, 2020
 * 
 */
public class MS_Compare_Two_Lists {
	private int[][] compareLists(int[] arr1, int[] arr2) {
		int i = 0;
		int j = 0;
		int len1 = arr1.length;
		int len2 = arr2.length;
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();

		while (i < len1 && j < len2) {
			int i1 = arr1[i];
			int i2 = arr2[j];
			if (i1 == i2) {
				l2.add(i1);
				i++;
				j++;
				while (i < len1 && arr1[i] == arr1[i - 1]) {
					i++;
				}
				while (j < len2 && arr2[j] == arr2[j - 1]) {
					j++;
				}

			} else if (i1 < i2) {
				l1.add(i1);
				i++;
				while (i < len1 && arr1[i] == arr1[i - 1]) {
					i++;
				}
			} else {
				l1.add(i2);
				j++;
				while (j < len2 && arr2[j] == arr2[j - 1]) {
					j++;
				}
			}
		}

		while (i < len1) {
			l1.add(arr1[i]);
			i++;
			while (i < len1 && arr1[i] == arr1[i - 1]) {
				i++;
			}
		}
		while (j < len2) {
			l1.add(arr2[j]);
			j++;
			while (j < len2 && arr2[j] == arr2[j - 1]) {
				j++;
			}
		}

		int[][] res = new int[2][];
		res[0] = new int[l1.size()];
		res[1] = new int[l2.size()];
		for (i = 0; i < l1.size(); i++) {
			res[0][i] = l1.get(i);
		}
		for (i = 0; i < l2.size(); i++) {
			res[1][i] = l2.get(i);
		}
		return res;
	}

	private void quickSort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}
//	    Random rand = new Random();
//	    int pivotIndex = left + rand.nextInt(right - left + 1);
//	    swap(array, pivotIndex, right);
		int pivot = array[right];
		int i = left, j = right - 1;
		while (i <= j) {
			if (array[i] <= pivot) {
				i++;
			} else {
				swap(array, i, j);
				j--;
			}
		}
		swap(array, right, i);
		quickSort(array, left, i - 1);
		quickSort(array, i + 1, right);

	}

	private void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void main(String[] args) {
		MS_Compare_Two_Lists ctl = new MS_Compare_Two_Lists();
		int[] arr1 = { 1, 4, 2, 2, 6, 6, 8 };
		int[] arr2 = { 4, 2, 2, 3, 3, 3, 7, 7, 7, 7 };
		ctl.quickSort(arr1, 0, arr1.length - 1);
		ctl.quickSort(arr2, 0, arr2.length - 1);
		System.out.println(ctl.compareLists(arr1, arr2));
		
	}
}
