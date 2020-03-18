package microsoft.Onsite;

/**
 * @author franksun
 * 
 * Feb 20, 2020
 * 
 */

//We can judge whether s1 and s2 can scramble into each other through mathematical induction:
//
//The base case that s1 can scramble into s2 if s1== s2. If the frequencies of each characters appearing in s1 and s2 differ, then s1 can not scramble into s2.
//
//If there exist 0 <= i <= s1.length() where
//
//s1[0,i] can scramble into s2[0,i] and s1[i,length] can scramble into s2[i, length]; or
//s1[0,i] can scramble into s2[length - i, length] and s1[i,length] can scramble into s2[0, length - i]
//then, s1 can scramble into s2.
public class LC_87_Scramble_String {
	public boolean isScramble(String s1, String s2) {
		if(s1.equals(s2)) {
			return true;
		}
		if (s1.length() != s2.length()) {
			return false;
		}
		int[] count = new int[26];
		for (int i = 0; i < s1.length(); i++) {
			count[s1.charAt(i) - 'a']++;
			count[s2.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] != 0) {
				return false;
			}
		}
		for(int i = 1; i < s1.length(); i++) {
			if (isScramble(s1.substring(0,i), s2.substring(0,i)) 
					&& isScramble(s1.substring(i), s2.substring(i))) {
				return true;
			} 
			if (isScramble(s1.substring(0,i), s2.substring(s2.length() - i))
					&& isScramble(s1.substring(i), s2.substring(0,s2.length()- i))) {
				return true;
			}
		}
		return false;
	}
}
