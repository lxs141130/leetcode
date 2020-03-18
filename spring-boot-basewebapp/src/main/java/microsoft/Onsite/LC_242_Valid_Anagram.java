package microsoft.Onsite;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 *         Feb 19, 2020
 * 
 */
public class LC_242_Valid_Anagram {
	public boolean isAnagram(String s, String t) {
		if (s == null && t == null) {
			return true;
		}
		if (s == null || t == null) {
			return false;
		}
		if (s.length() != t.length()) {
			return false;
		}
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int cnt = map.getOrDefault(c, 0);
			cnt++;
			map.put(c, cnt);
		}
		for (int i = 0 ; i < t.length(); i++) {
			char c = t.charAt(i);
			if (!map.containsKey(c)) {
				return false;
			}
			int cnt = map.get(c);
			if (cnt == 1) {
				map.remove(c);
			} else {
				cnt--;
				map.put(c, cnt);
			}
		}
		return map.isEmpty();
	}
	
	public static void main(String[] args) {
		LC_242_Valid_Anagram va = new LC_242_Valid_Anagram();
		System.out.println(va.isAnagram("anagram", "nagaram"));
		
		System.out.println(va.isAnagram("rat", "car"));
	}
}
