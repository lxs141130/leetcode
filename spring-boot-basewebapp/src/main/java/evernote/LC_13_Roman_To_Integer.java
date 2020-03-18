package evernote;

import java.util.HashMap;
import java.util.Map;

/**
 * @author franksun
 * 
 * Mar 8, 2020
 * 
 */
public class LC_13_Roman_To_Integer {
	
	public int romanToInt(String s) {
        
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		
		int i = s.length() - 1;
        int rst =  map.get(s.charAt(i--));
        while (i >= 0) {
        	int currentVal = map.get(s.charAt(i));
        	int lastVal = map.get(s.charAt(i + 1));
        	if (currentVal >= lastVal) {
        		rst += currentVal;
        	} else {
        		rst -= currentVal;
        	}
        	i--;
        }
        return rst;
    }
	
	public static void main(String[] args) {
		LC_13_Roman_To_Integer rti = new LC_13_Roman_To_Integer();
		
		//MCCLIV ->1254
		System.out.println(rti.romanToInt("MCCLIV"));
		//MMCCXXII
		System.out.println(rti.romanToInt("MMCCXXII"));
		//CXXXIV
		System.out.println(rti.romanToInt("CXXXIV"));
	}
}

