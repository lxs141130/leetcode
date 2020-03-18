package com.example.code;

public class LC_1043_Partition_Array_For_Maximum_Sum {
	public int maxSumAfterPartitioning(int[] A, int K) {
		int[] memo = new int[A.length];
		memo[0] = A[0];
		int initMax = A[0];

		for (int i = 1; i < K; ++i) {
			if (A[i] > initMax) {
				initMax = A[i];
			}
			memo[i] = (i + 1) * initMax;
		}

		for (int i = K; i < A.length; ++i) {
			int cur = 0;
			int kIntervalMax = A[i];
			// Backtrack up to K-1 indices to calculate current maximum for memo[i].
			for (int j = 1; j <= K; ++j) {
				// Keep track of the current maximum in the window [i-j+1, i].
				if (A[i - j + 1] > kIntervalMax) {
					kIntervalMax = A[i - j + 1];
				}
				// cur is the candidate for the solution to memo[i] as we backtrack the K-1
				// window.
				cur = memo[i - j] + j * kIntervalMax;

				if (cur > memo[i]) {
					memo[i] = cur;
				}
			}
		}
		return memo[A.length - 1];
	}

	public int maxSumAfterPartitioning1(int[] A, int K) {

		int n = A.length;

		int dp[] = new int[n + 1];

		for (int i = 1; i <= n; i++) {

			int best = -1;
			int max = A[i - 1];

			for (int j = 0; j < K && i - 1 - j >= 0; j++) {
				max = Math.max(max, A[i - 1 - j]);
				best = Math.max(best, dp[i - 1 - j] + (j + 1) * max);
			}
			dp[i] = best;
		}
		return dp[n];
	}

	public static void main(String[] args) {
		LC_1043_Partition_Array_For_Maximum_Sum pams = new LC_1043_Partition_Array_For_Maximum_Sum();
		int[] A = { 1, 15, 7, 9, 2, 5, 10 };
		int K = 3;
		System.out.print(pams.maxSumAfterPartitioning1(A, K));
	}
}
