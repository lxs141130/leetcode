package com.example.code;

public class LC_91_Decode_Ways {

	public int numDecodings(String s) {
		// write your code here
		int rst = 0;
		if (s == null || s.length() == 0 || !isValid(s.substring(0, 1))) {
			return rst;
		}
		int[] dp = new int[s.length() + 1];
		dp[0] = 1;
		for (int i = 1; i <= s.length(); i++) {
			if (isValid(s.substring(i - 1, i))) {
				dp[i] += dp[i - 1];
			}
			if (i > 1 && isValid(s.substring(i - 2, i))) {
				dp[i] += dp[i - 2];
			}
		}
		return dp[s.length()];
	}

	private boolean isValid(String s) {
		if (s.charAt(0) == '0') {
			return false;
		}
		int num = Integer.parseInt(s);
		if (num < 1 || num > 26) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		LC_91_Decode_Ways dw = new LC_91_Decode_Ways();
		String s = "12";
		System.out.print(dw.numDecodings(s));
	}
}
