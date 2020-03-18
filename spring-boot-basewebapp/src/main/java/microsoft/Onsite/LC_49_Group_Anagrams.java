package microsoft.Onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author franksun
 * 
 *         Feb 19, 2020
 * 
 */
public class LC_49_Group_Anagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> rst = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			char[] charArray = str.toCharArray();
			Arrays.sort(charArray);

			String sortedStr = Arrays.toString(charArray);
			List<String> list = map.getOrDefault(sortedStr, new ArrayList<>());
			list.add(str);
			map.put(sortedStr, list);
		}
		
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			rst.add(entry.getValue());
		}
		return rst;
	}
	
	public static void main(String[] args) {
		LC_49_Group_Anagrams ga = new LC_49_Group_Anagrams();
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(ga.groupAnagrams(strs));
	}
}
