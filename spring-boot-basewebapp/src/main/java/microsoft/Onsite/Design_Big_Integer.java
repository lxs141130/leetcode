package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Feb 25, 2020
 * 
 */
public class Design_Big_Integer {

	public BigInt add(BigInt b1, BigInt b2) {
		if (b1.sign == 0) {
			return b2;
		}
		if (b2.sign == 0) {
			return b1;
		}

		if (b1.sign != b2.sign && compareValue(b1.val, b2.val) == 0) {
			return new BigInt(0, "0");
		}

		int lenB1 = b1.val.length();
		int lenB2 = b2.val.length();

		int diff = 0;
		// make b1, b2 with the same length, add leading zero
		if (lenB1 < lenB2) {
			diff = lenB2 - lenB1;
			for (int i = 0; i < diff; i++) {
				b1.val = "0" + b1.val;
			}
		} else if (lenB1 > lenB2) {
			diff = lenB1 - lenB2;
			for (int i = 0; i < diff; i++) {
				b2.val = "0" + b2.val;
			}
		}
		BigInt rst = new BigInt();
		int maxLen = Math.max(lenB1, lenB2);

		StringBuilder sb = new StringBuilder();

		// compare value to determine the sign for result
		int carry = 0;
		if (b1.sign == b2.sign) {
			rst.sign = b1.sign;
			// add
			for (int i = maxLen - 1; i >= 0; i--) {
				char cb1 = b1.val.charAt(i);
				char cb2 = b2.val.charAt(i);
				int sum = (cb1 - '0') + (cb2 - '0') + carry;
				carry = sum / 10;
				sb.append(sum % 10);
			}
			if (carry == 1) {
				sb.append(1);
			}
			rst.val = sb.reverse().toString();
		} else {
			rst.sign = compareValue(b1.val, b2.val) == 1 ? b1.sign : b2.sign;
			// substract
			for (int i = maxLen - 1; i >= 0; i--) {
				char cb1 = b1.val.charAt(i);
				char cb2 = b2.val.charAt(i);
				int sub = rst.sign == 1 ? (cb1 - '0') - (cb2 - '0') - carry : (cb2 - '0') - (cb1 - '0') - carry;
				if (sub < 0) {
					sub = sub + 10;
					carry = 1;
				} else {
					carry = 0;
				}
				sb.append(sub);
			}
			rst.val = sb.reverse().toString();
		}

		// remove leading zero
		int i = 0;
		while (i < rst.val.length() && rst.val.charAt(i) == '0') {
			i++;
		}
		rst.val = rst.val.substring(i);

		return rst;

	}

	public int compareValue(String s1, String s2) {
		if (s1.length() == s2.length()) {
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) == s2.charAt(i)) {
					continue;
				} else {
					return s1.charAt(i) - s2.charAt(i) > 0 ? 1 : -1;
				}
			}
			return 0;
		} else {
			return s1.length() > s2.length() ? 1 : -1;
		}
	}

	public static void main(String[] args) {
		Design_Big_Integer dbi = new Design_Big_Integer();
		BigInt b1 = new BigInt(1, "1");
		BigInt b2 = new BigInt(1, "9999");
		System.out.println(dbi.add(b1, b2));
	}

	static class BigInt {
		int sign;
		String val;

		public BigInt() {
		}

		public BigInt(int sign, String val) {
			this.sign = sign;
			this.val = val;
		}
	}
}
