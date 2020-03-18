package microsoft.OTAs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franksun
 * 
 *         Feb 14, 2020
 * 
 */

//time -> O(N)
//space -> O(N)
public class MS_Fizz_Buzz {
	public static List<String> fizzBuzz(int n) {
		List<String> rst = new ArrayList<String>();
		for (int i = 1; i <= n; i++) {
			StringBuilder sb = new StringBuilder();
			if (i % 2 == 0) {
				sb.append("Codility");
			}
			if (i % 3 == 0) {
				sb.append("Test");
			}
			if (i % 5 == 0) {
				sb.append("Coders");
			}
			if (sb.length() == 0) {
				sb.append(i);
			}
			rst.add(sb.toString());
		}
		return rst;
	}

	public static void main(String[] args) {
		System.out.println(fizzBuzz(15));
	}
}
