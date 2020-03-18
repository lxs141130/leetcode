package indeed;

/**
 * @author franksun
 * 
 *         Feb 10, 2020
 * 
 */
public class LC_985_Sum_Of_Even_Numbers_After_Queries {
	public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
		int[] res = new int[queries.length];
		int evenSum = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] % 2 == 0) {
				evenSum += A[i];
			}
		}
		for (int i = 0; i < queries.length; i++) {
			int val = queries[i][0];
			int index = queries[i][1];
			// if A[index] is an even number, just remomve it first
			if (A[index] % 2 == 0) {
				evenSum -= A[index];
			}
			// adding the val to A[index]
			A[index] += val;
			// if A[index] is even now, add it back
			if (A[index] % 2 == 0) {
				evenSum += A[index];
			}
			res[i] = evenSum;

			// even + even -> even evenSum - A[index] + (A[index] + val)
			// even + odd -> odd evenSum - A[index]
			// odd + odd -> even evenSum + (val + A[index])
			// odd + even -> odd evenSum
		}
		return res;
	}

	public static void main(String[] args) {
		LC_985_Sum_Of_Even_Numbers_After_Queries en = new LC_985_Sum_Of_Even_Numbers_After_Queries();
		int[] A = { 3, 2 };
		int[][] queries = { { 4, 0 }, { 3, 0 } };
		System.out.println(en.sumEvenAfterQueries(A, queries));
	}
}
