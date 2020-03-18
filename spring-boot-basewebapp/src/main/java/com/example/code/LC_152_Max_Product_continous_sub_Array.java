package com.example.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LC_152_Max_Product_continous_sub_Array {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        int rst = Math.max(1, nums[0]);
        max[0] = nums[0];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++ ) {
        	max[i] = Math.max(Math.max(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
        	min[i] = Math.min(Math.min(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
        	rst = Math.max(rst, max[i]);
        }
        return rst;
    }
    
    public static void main(String[] args) {
    	LC_152_Max_Product_continous_sub_Array mp = new LC_152_Max_Product_continous_sub_Array();
    	int[] nums = {2,3,-2,-4,-20,0,30};
    	System.out.println(mp.maxProduct(nums));
    	
    	List<String> list = Arrays.asList("1","10","21","210","9");
    	Collections.sort(list);
    	Collections.reverse(list);
    	System.out.println(list);
    	
    	List<String> rst= new ArrayList<>();
    	Collections.sort(rst, (s1,s2) -> s1.compareTo(s2));
    	System.out.println(list);
	}
    
}
