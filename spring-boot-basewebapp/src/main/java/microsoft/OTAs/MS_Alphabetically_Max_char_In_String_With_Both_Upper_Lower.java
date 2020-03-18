package microsoft.OTAs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 *         Feb 14, 2020
 * 
 */

//给一个string，输出alphabetically最大的，而且同时出现大写和小写的字母。
//比如说 "aAbBcD"，则返回B。因为c和D没有大小写同时出现，A和B都有大小写，但是B比A大，所以返回B。如果不存在这种数字，return "NO"
public class MS_Alphabetically_Max_char_In_String_With_Both_Upper_Lower {
	public static String maxAlphabeticalChar(String s) {
		if (s == null || s.length() < 2) {
			return "No";
		}
		Map<Character, Character> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, c);
		}
		char candidate = '0';
		for (char c : s.toCharArray()) {
			if (c <= 'Z' && map.containsKey((char)(c + 32))) {
				if (c > candidate) {
					candidate = c;
				}
			}
		}
		return candidate == '0' ? "No" : candidate+"";
	}

	public static void main(String[] args) {
		// B
		System.out.println(maxAlphabeticalChar("aAbBcD"));
		// A
		System.out.println(maxAlphabeticalChar("aAbcD"));
		// No
		System.out.println(maxAlphabeticalChar("kAdBcE"));

	}
}
