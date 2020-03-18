package microsoft.OTAs;

/**
 * @author franksun
 * 
 *         Feb 14, 2020
 * 
 */

//time -> O(N^2)
//space ->O(N)
public class MS_Remove_Elements_Make_Array_Increase_Then_Decrease {
	// 双端 最长增长substring 问题， 然后多出的就是要删除的
	public static int solution(int[] arr) {
		int ans = Integer.MAX_VALUE;
		int[] dp = new int[arr.length];
		int[] dp2 = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && dp[j] > max) {
					max = dp[j];
				}
			}
			dp[i] = max + 1;
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			int max = 0;
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[i] > arr[j] && dp2[j] > max) {
					max = dp2[j];
				}
			}
			dp2[i] = max + 1;
		}

		for (int i = 0; i < arr.length; i++) {
			ans = Math.min(ans, arr.length - dp[i] - dp2[i] + 1);
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 0, 2, 1, 5, 3, 6, 4, 8, 9, 7 }));
	}
}
