package microsoft.OTAs;

import java.util.Arrays;
import java.util.List;

/**
 * @author franksun
 * 
 * Feb 11, 2020
 * 
 */

//https://leetcode.com/discuss/interview-question/401826/
//
//A = ["co","dil","ity"] , function should return 5, resulting string S could be codil , dilco, coity,ityco
//A = ["abc","kkk","def","csv"] , returns 6 , resulting Strings S could be abcdef , defabc, defcsv , csvdef
//A = ["abc","ade","akl"] , return 3

//time -> O(N^2)
//space -> O(N)
public class MS_Concatenated_String_Length_with_unique_Characters {
    private int max = 0;
    public int maxLength(List<String> arr) {
        dfs(arr, 0, "");
        return max;
    }

    public void dfs(List<String> arr, int index, String concatenatStr) {
        //candidates for Maximum Length of a Concatenated String with Unique Characters
    	if (isUnique(concatenatStr)) {
        	max = Math.max(max, concatenatStr.length());
        }
        //invalid entry, just return
    	if (index == arr.size() || !isUnique(concatenatStr)) {
        	return;
        }
        for (int i = index; i < arr.size(); i++) {
            dfs(arr, i + 1, concatenatStr + arr.get(i));
        }
    }
    
    //method to check if all the char in s are unique
    public boolean isUnique(String s) {
        int[] alpha = new int[26];
        for (int i = 0; i < s.length(); i++) alpha[s.charAt(i) - 'a']++;
        for (int i = 0; i < alpha.length; i++) if (alpha[i] > 1) return false;
        return true;
    }
    
    public static void main(String[] args) {
    	MS_Concatenated_String_Length_with_unique_Characters cs = new MS_Concatenated_String_Length_with_unique_Characters();
		List<String> list1 =  Arrays.asList("co","dil","ity");
		System.out.println(cs.maxLength(list1));
		
		cs.max = 0;
		List<String> list2 =  Arrays.asList("abc","kkk","def","csv");
		System.out.println(cs.maxLength(list2));
		
		cs.max = 0;
		List<String> list3 =  Arrays.asList("abc","ade","akl");
		System.out.println(cs.maxLength(list3));
	}
}
