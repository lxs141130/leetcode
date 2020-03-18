package evernote;

/**
 * @author franksun
 * 
 * Mar 8, 2020
 * 
 */
public class LC_12_Integer_To_Roman {
	public String intToRoman(int n) {
		if (n < 1 || n > 3999) {
			return ""; 
		}
		int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] symbols = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV","I"};
		
		StringBuilder sb = new StringBuilder();
		int digit = 0;
		while (n > 0) {
			int time = n / nums[digit];
			n -= time * nums[digit];
			while (time > 0) {
				sb.append(symbols[digit]);
				time--;
			}
			digit++;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		LC_12_Integer_To_Roman itr = new LC_12_Integer_To_Roman();
		
		System.out.println(itr.intToRoman(1258));
		
		System.out.println(itr.intToRoman(2222));
		
		System.out.println(itr.intToRoman(134));
	}
}

//255
