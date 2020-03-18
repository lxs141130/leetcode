package microsoft.OTAs;

/**
 * @author franksun
 * 
 * Feb 11, 2020
 * 
 */

//time -> O(N) N is the length of input s
//space -> O(N) N is the length of input s
public class MS_String_Without_3_Identical_Consecutive_Letters {
	public String solution(String s) { 
		//corner case
		if (s == null || s.length() < 3) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s);
		//reason go from last to beginning is to make sure sb.delete char always delete the correct index
		int i = s.length() - 1;
		int j = s.length() - 1;
		int counter = 0;
		
		while (j >= 0) {
			if (s.charAt(i) == s.charAt(j)) {
				counter++;
				if (counter == 3) {
					sb.deleteCharAt(j);
					counter--;
				}
				j--;
			} else {
				counter = 0;
				i = j;
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		MS_String_Without_3_Identical_Consecutive_Letters mm = new MS_String_Without_3_Identical_Consecutive_Letters();
		String A1 = "aaabaaaaa";
		System.out.println(mm.solution(A1));
		
		String A2 = "eedaaad";
		System.out.println(mm.solution(A2));
		
		String A3 = "xxxtxxx";
		System.out.println(mm.solution(A3));
		
		String A4 = "uuuuxaaaaxuuu";
		System.out.println(mm.solution(A4));
		
	}
}
