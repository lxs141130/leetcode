package indeed;

/**
 * @author franksun
 * 
 *         Feb 9, 2020
 * https://leetcode.com/problems/consecutive-numbers-sum/discuss/129227/JAVA-easy-4-lines-O(n0.5)
 */
public class LC_829_Consecutive_Numbers_Sum {
	public int consecutiveNumbersSum(int N) {
		int sum = 0;
		int ans = 0;
		for (int i = 1; sum < N; i++) {
			sum += i;
			//k = (N-sum)/i, and the N = (k+1) + (k + 1 + 1)... total number is i
			if ((N - sum) % i == 0) {
				ans++;
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		LC_829_Consecutive_Numbers_Sum sn = new LC_829_Consecutive_Numbers_Sum();
		System.out.println(sn.consecutiveNumbersSum(5));
	}
}

