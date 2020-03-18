package microsoft.OTAs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 * Feb 11, 2020
 * 
 */

//Time complexity: O(1)
//Space complexity: O(1)

public class MS_Day_Of_Week {
	public String solution(String s, int k){
		String[] week = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < week.length; i++) {
			map.put(week[i], i);
		}
		return week[(map.get(s) + k ) % 7];
	}
}
