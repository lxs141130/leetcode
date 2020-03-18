package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Feb 18, 2020
 * 
 */
public class LC_564_Find_The_Closest_Palindrome {

	public static String mirroring(String s) {
		String x = s.substring(0, (s.length()) / 2);
		return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse().toString();
	}

	public static String nearestPalindromic1(String n) {
		if (n.equals("1"))
			return "0";

		String a = mirroring(n);
		long diff1 = Long.MAX_VALUE;
		diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
		if (diff1 == 0)
			diff1 = Long.MAX_VALUE;

		StringBuilder s = new StringBuilder(n);
		int i = (s.length() - 1) / 2;
		while (i >= 0 && s.charAt(i) == '0') {
			s.replace(i, i + 1, "9");
			i--;
		}
		if (i == 0 && s.charAt(i) == '1') {
			s.delete(0, 1);
			int mid = (s.length() - 1) / 2;
			s.replace(mid, mid + 1, "9");
		} else
			s.replace(i, i + 1, "" + (char) (s.charAt(i) - 1));
		String b = mirroring(s.toString());
		long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));

		s = new StringBuilder(n);
		i = (s.length() - 1) / 2;
		while (i >= 0 && s.charAt(i) == '9') {
			s.replace(i, i + 1, "0");
			i--;
		}
		if (i < 0) {
			s.insert(0, "1");
		} else
			s.replace(i, i + 1, "" + (char) (s.charAt(i) + 1));
		String c = mirroring(s.toString());
		long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

		if (diff2 <= diff1 && diff2 <= diff3)
			return b;
		if (diff1 <= diff3 && diff1 <= diff2)
			return a;
		else
			return c;
	}

	public static Long mirror(Long x) {
		String temp = String.valueOf(x);
		char[] arr = temp.toCharArray();
		int i = 0;
		int j = temp.length() - 1;
		while (i < j) {
			arr[j--] = arr[i++];
		}
		return Long.valueOf(new String(arr));
	}

	public static String nearestPalindromic2(String n) {
		int order = (int) Math.pow(10, (n.length() / 2));
		Long ans = Long.valueOf(new String(n));
		Long nochange = mirror(ans);
		Long large = mirror((ans / order) * order + order + 1);
		Long small = mirror((ans / order) * order - 1);
		if (nochange > ans)
			large = (long) Math.min(nochange, large);
		else if (nochange < ans)
			small = (long) Math.max(nochange, small);
		if (ans - small <= large - ans)
			return String.valueOf(small);
		else
			return String.valueOf(large);
	}

	public static void main(String[] args) {
		//use large
		System.out.println(nearestPalindromic1("10099"));
		//use noChange
		System.out.println(nearestPalindromic2("10004"));
		//use small
		System.out.println(nearestPalindromic2("99000"));
	}
}
