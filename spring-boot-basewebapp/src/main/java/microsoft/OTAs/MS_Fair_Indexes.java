package microsoft.OTAs;

/**
 * @author franksun
 * 
 *         Feb 12, 2020
 * 
 */

//time -> O(N)
//space -> O(1)
public class MS_Fair_Indexes {
	public static void main(String[] args) {
		int[] A1 = { 4, -1, 0, 3 }, B1 = { -2, 5, 0, 3 };
		int[] A2 = { 2, -2, -3, 3 }, B2 = { 0, 0, 4, -4 };
		int[] A3 = { 4, -1, 0, 3 }, B3 = { -2, 6, 0, 4 };
		int[] A4 = { 3, 2, 6 }, B4 = { 4, 1, 6 };
		int[] A5 = { 1, 4, 2, -2, 5 }, B5 = { 7, -2, -2, 2, 5 };
		System.out.println(getNumOfFairIndexes(A1, B1));
		System.out.println(getNumOfFairIndexes(A2, B2));
		System.out.println(getNumOfFairIndexes(A3, B3));
		System.out.println(getNumOfFairIndexes(A4, B4));
		System.out.println(getNumOfFairIndexes(A5, B5));
	}

	private static int getNumOfFairIndexes(int[] A, int[] B) {
		//corner case
		if (A.length != B.length) {
			return 0;
		}
		int ans = 0;
		//sumA is the total sum of elements in array A
		int sumA = 0;
		//sumB is the total sum of elements in array B
		int sumB = 0;
		for (int i = 0; i < A.length; i++) {
			sumA += A[i];
			sumB += B[i];
		}
		//current sum of elements in array A
		int curSumA = 0;
		//current sum of elements in array B
		int curSumB = 0;
		for (int i = 0; i < A.length - 1; i++) {
			curSumA += A[i];
			curSumB += B[i];
			if (sumA == 2 * curSumA && sumB == 2 * curSumB && curSumA == curSumB)
				ans++;
		}
		return ans;
	}
	
	private static int getNumOfFairIndexes1(int[] A, int[] B) {
		if (A.length != B.length) {
			return 0;
		}
		int len = A.length;
		int[] sumA = new int[len + 1];
		int[] sumB = new int[len + 1];

		for (int i = 1; i <= len; i++) {
			sumA[i] = sumA[i - 1] + A[i - 1];
			sumB[i] = sumB[i - 1] + B[i - 1];
		}
		int rst = 0;
		for (int k = 1; k < len; k++) {
			if (sumA[k] == sumA[len] - sumA[k] && sumB[k] == sumB[len] - sumB[k] && sumA[k] == sumB[k]) {
				rst++;
			}
		}
		return rst;
	}
}
