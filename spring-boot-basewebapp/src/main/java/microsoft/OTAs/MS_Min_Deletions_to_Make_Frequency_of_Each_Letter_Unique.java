package microsoft.OTAs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author franksun
 * 
 *         Feb 11, 2020
 * 
 */

//time -> O(N)
//space -> O(N)
public class MS_Min_Deletions_to_Make_Frequency_of_Each_Letter_Unique {
	public static int minDeletion(String s) {
		if (s == null || s.length() <= 1) {
			return 0;
		}

		Map<Character, Integer> countMap = new HashMap<>();
		//O(N) N is the length of s
		for (int i = 0; i < s.length(); i++) {
			countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);
		}

		List<Integer> freq = new ArrayList<>(countMap.values());
		
		//O(k*log(k)) -> k is the size of freq list
		Collections.sort(freq, Collections.reverseOrder());

		int deleted = 0;
		Set<Integer> countSet = new HashSet<>();

		//O(k) -> k is the size of freq list
		for (int n : freq) {
			if (!countSet.contains(n)) {
				countSet.add(n);
				continue;
			}
			while (countSet.contains(n)) {
				n--;
				deleted++;
			}
			if (n != 0) {
				countSet.add(n);
			}
		}
		return deleted;
	}
	
	public static void main(String[] args) {
		String s1 = "aaaabbbb";
		System.out.println(s1 + ":" + minDeletion(s1));
		String s2 = "aabbbbcccdddd";
		System.out.println(s2 + ":" + minDeletion(s2));
		String s3 = "aaaaaabbbbbccccddddeeeeee";
		System.out.println(s3 + ":" + minDeletion(s3));
		String s4 = "abcdefghijkl";
		System.out.println(s4 + ":" + minDeletion(s4));
		String s5 = "aaaaaa";
		System.out.println(s5 + ":" + minDeletion(s5));
		String s6 = "aabbffddeaee";
		System.out.println(s6 + ":" + minDeletion(s6));
		String s7 = "llll";
		System.out.println(s7 + ":" + minDeletion(s7));
		String s8 = "example";
		System.out.println(s8 + ":" + minDeletion(s8));
	}
}
