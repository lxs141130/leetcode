package com.example.code;

import java.util.ArrayList;
import java.util.List;

public class LC_151_Reverse_Word_In_String {
	public String reverseWords(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return "";
        }
        String rst = "";
        s = s.trim();
        List<String> res = new ArrayList<>();
        s = reverse(s);
        String[] arr = s.split("\\s+");
        for (int i = 0; i < arr.length; i++) {
            res.add(reverse(arr[i]));
        }
        rst = String.join(" ", res);
        return rst; 
    }
    
    private String reverse(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] arr = s.toCharArray();
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return new String(arr);
    }
    
    public static void main(String[] args) {
    	LC_151_Reverse_Word_In_String rw = new LC_151_Reverse_Word_In_String();
    	String s = "  the   sky is     blue ";
    	System.out.println(rw.reverseWords(s));
	}
}
