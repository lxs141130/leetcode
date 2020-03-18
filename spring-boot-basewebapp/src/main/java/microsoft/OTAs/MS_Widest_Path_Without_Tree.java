package microsoft.OTAs;

import java.util.Arrays;

/**
 * @author franksun
 * 
 *         Feb 12, 2020
 * 
 */

//time -> O(N) for comparison, O(Nlog(N)) for arrays sort, N is the size of X
// 	when N is largr than 10, time is O(Nlog(N)), else time is O(N)

//space -> O(1)
public class MS_Widest_Path_Without_Tree {
	public static int findVerticalPath(int[] X, int[] Y) {
		int maxWidth = 0;
		//O(Nlog(N))
		Arrays.sort(X);

		for (int i = 0; i < X.length - 1; i++) {
			maxWidth = Math.max(maxWidth, (X[i + 1] - X[i]));
		}
		return maxWidth;
	}

	public static void main(String[] args) {
		// 2
		int[] X1 = { 5, 5, 5, 7, 7, 7 };
		int[] Y1 = { 3, 4, 5, 1, 3, 7 };
		System.out.println(findVerticalPath(X1, Y1));

		// 4
		int[] X2 = { 6, 10, 1, 4, 3 };
		int[] Y2 = { 2, 5, 3, 1, 6 };
		System.out.println(findVerticalPath(X2, Y2));

		// 3
		int[] X3 = { 4, 1, 5, 4 };
		int[] Y3 = { 4, 5, 1, 3 };
		System.out.println(findVerticalPath(X3, Y3));
	}
}
