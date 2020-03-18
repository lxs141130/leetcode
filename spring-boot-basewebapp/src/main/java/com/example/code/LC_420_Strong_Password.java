package com.example.code;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class LC_420_Strong_Password {
	public int strongPasswordChecker(String s) {
		int res = 0;
		int lower = 1;
		int upper = 1;
		int digit = 1;
		int n = s.length();
		int repeating[] = new int[n];
		for (int i = 0; i < n;) {
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				lower = 0;
			}
			if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
				upper = 0;
			}
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				digit = 0;
			}
			int j = i;
			while (i < n && s.charAt(i) == s.charAt(j)) {
				i++;
			}
			repeating[j] = i - j;
		}
		int missingType = lower + upper + digit;
		if (n < 6) {
			res = Math.max(missingType, 6 - n); 
		} else {
			int over = Math.max(0, n - 20);
			res += over;
			int replace = 0;
			for (int i = 0; i < n && over > 0; i++) {
				if (repeating[i] < 3) {
					continue;
				} 
				if (repeating[i] % 3 == 0) {
					repeating[i] -= 1;
					over -= 1;
				}
			}
			
			for (int i=0; i<n && over > 0; i++) {
                if (repeating[i] < 3) continue;  
                if (repeating[i] % 3 == 1) {  //e.g. 7 a's, need to remove 2 to become 3*1+2=5
                	repeating[i] -= Math.min(2, over);
                    over -= 2;
                }
            }
			
			for (int i = 0; i < n; i++) {
				if (repeating[i] >= 3 && over > 0) {
					int needToRemove = repeating[i] - 2;
					repeating[i] -= over;
					over -= needToRemove;
					
				}
				if (repeating[i] >= 3) {
					replace += repeating[i] / 3;
				}
			}
			res += Math.max(replace, missingType);
		}
		return res;
	}
	
	public static void main(String[] args) {
//		LC_420_Strong_Password sp = new LC_420_Strong_Password();
//		String s = "aaaaaaa1234567890123456B1234";
//		System.out.println(sp.strongPasswordChecker(s));
//		String s1 = "1234567890123456Baaa";
//		System.out.println(sp.strongPasswordChecker(s1));
		
        
//        ZoneId LA = ZoneId.of("America/Los_Angeles");
        ZoneId GMT1 = ZoneId.of("Europe/London");
        
        LocalDateTime PT = LocalDateTime.now();
        LocalDateTime GMT = LocalDateTime.now(GMT1);
        long betweenHours = ChronoUnit.HOURS.between( PT, GMT);
        
        System.out.println(betweenHours);

	}
	
}
