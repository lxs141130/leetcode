package indeed;

import java.util.Stack;

/**
 * @author franksun
 * 
 *         Mar 10, 2020
 * 
 */
public class LC_227_Basic_Calculator_II {
	public int calculate(String s) {
		if (s == null) {
			return 0;
		}
		s = String.join("", s.trim().split("\\s+"));
		int len = s.length();
		if (len == 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<>();
		int res = 0;
		int curNum = 0;
		char sign = '+';// prev sign
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				curNum = curNum * 10 + c - '0';
			}
			if (i == len - 1 || !Character.isDigit(c)) {
				if (sign == '+') {
					stack.push(curNum);
				} else if (sign == '-') {
					stack.push(-curNum);
				} else if (sign == '*') {
					stack.push(stack.pop() * curNum);
				} else if (sign == '/') {
					stack.push(stack.pop() / curNum);
				}
				sign = c;// can be a sign or not
				curNum = 0;
			}
		}

		while (!stack.isEmpty()) {
			res += stack.pop();
		}

		return res;
	}

	public static void main(String[] args) {
		LC_227_Basic_Calculator_II bc = new LC_227_Basic_Calculator_II();
		System.out.println(bc.calculate("3 + 2 * 2"));
	}
}
