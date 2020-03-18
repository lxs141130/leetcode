package com.example.code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC_215_Kth_Largest_Elem {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return 0;
        }
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left == right) {
            return nums[left];
        }
        int partitionIndex = partition(nums, left, right);
        if (partitionIndex == targetIndex) {
            return nums[partitionIndex];
        }else if (partitionIndex < targetIndex) {
            return quickSelect(nums, partitionIndex + 1, right, targetIndex);
        }else {
            return quickSelect(nums, left, partitionIndex - 1, targetIndex);
        }
    }
    
    public int partition(int[] nums, int left, int right) {
        
        int pivot = nums[right];
        int storedIndex = left;
        //move all smaller elems to the left
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                int temp = nums[storedIndex];
                nums[storedIndex] = nums[i];
                nums[i] = temp;
                storedIndex++;
            }
        }
        //move pivot to the final place;
        int temp = pivot;
        pivot = nums[storedIndex];
        nums[storedIndex] = temp;

        return storedIndex;
        
    }
	
	public static void main(String[] args) {
		LC_215_Kth_Largest_Elem tk = new LC_215_Kth_Largest_Elem();
		int[] nums = {3,2,1,5,6,4};
		int k = 2;
		System.out.println(tk.findKthLargest(nums, k));
		Set<String> operators = new HashSet<>();
		operators.addAll(Arrays.asList("+","-","*","/"));
	}
}
