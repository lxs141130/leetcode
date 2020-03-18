package com.example.code;

import java.util.HashMap;
import java.util.Map;

public class LC_525_Contiguous_Array {
	public int findMaxLength(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		Map<Integer, Integer> prefix = new HashMap<>();
		prefix.put(0, -1);
		int sum = 0;
		int rst = 0;
		for (int i = 0; i < len; i++) {
			if (nums[i] == 0) {
				sum--;
			} else {
				sum++;
			}

			if (prefix.containsKey(sum)) {
				rst = Math.max(rst, i - prefix.get(sum));
			} else {
				prefix.put(sum, i);
			}
		}

		return rst;
	}

	public static void main(String[] args) {
		LC_525_Contiguous_Array ca = new LC_525_Contiguous_Array();
		int[] array = { 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1,
				0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0,
				1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1 };
		System.out.print(ca.findMaxLength(array));
	}
}
