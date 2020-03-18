package microsoft.OTAs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franksun
 * 
 * Feb 12, 2020
 * 
 */

//https://leetcode.com/discuss/interview-question/414660/

//time -> O(N)
//space -> O(M) M is the number of R inside s
public class MS_MIN_SWAP_To_Group_Red_Balls {
	public static int solution(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		if (s.length() > 200000) {
			return -1;
		}
	    List<Integer> redIndices = getRedIndices(s);
	    int mid = redIndices.size() / 2;
	    long minSwaps = 0;
	    for (int i = 0; i < redIndices.size(); i++) {
	        // number of swaps for each R is the distance to mid, minus the number of R's between them
	        minSwaps += Math.abs(redIndices.get(mid) - redIndices.get(i)) - Math.abs(mid - i);
	    }
	    
	    return minSwaps > Integer.MAX_VALUE ? -1 : (int)minSwaps;
	}

	private static List<Integer> getRedIndices(String s) {
	    List<Integer> indices = new ArrayList<>(s.length());
	    for (int i = 0; i < s.length(); i++) {
	        if (s.charAt(i) == 'R') {
	            indices.add(i);
	        }
	    }
	    return indices;
	}
	
	public static void main(String[] args) {
		System.out.println(solution("WRRWWR"));
		System.out.println(solution("WWRWWWRWR"));
		System.out.println(solution("WWW"));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100000; i++) {
			sb.append("RW");
		}
		System.out.println(solution(sb.toString()));
		
	}
}
