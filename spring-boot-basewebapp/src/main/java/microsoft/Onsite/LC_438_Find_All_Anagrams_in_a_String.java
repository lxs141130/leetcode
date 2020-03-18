package microsoft.Onsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author franksun
 * 
 * Feb 19, 2020
 * 
 */
public class LC_438_Find_All_Anagrams_in_a_String {
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> rst = new ArrayList<>();
		int ns = s.length();
		int np = p.length();
		
	    if (ns < np) return rst;
		
	    Map<Character, Integer> sMap = new HashMap<>();
		Map<Character, Integer> pMap = new HashMap<>();
		
		for (int i = 0; i < np; i++) {
			char c = p.charAt(i);
			if (pMap.containsKey(c)) {
				pMap.put(c, pMap.get(c) + 1);
			} else {
				pMap.put(c, 1);
			}
		}
		//a1,b1,c1
		
		//cbaebabacd
		
		//b1 a1 c1
		for (int i = 0; i < ns; i++) {
			char c = s.charAt(i);
			if (sMap.containsKey(c)) {
				sMap.put(c, sMap.get(c) + 1);
			} else {
				sMap.put(c, 1);
			}
			
			if (i >= np) {
				c = s.charAt(i - np);
				if (sMap.get(c) == 1) {
					sMap.remove(c);
				} else {
					sMap.put(c, sMap.get(c) - 1);
				}
			}
			
			if (pMap.equals(sMap)) {
				rst.add(i - np + 1);
			}
		}
		return rst;
	}
}
