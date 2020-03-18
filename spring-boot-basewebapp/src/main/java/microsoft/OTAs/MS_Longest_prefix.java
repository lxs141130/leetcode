package microsoft.OTAs;

/**
 * @author franksun
 * 
 *         Feb 14, 2020
 * 
 */

//Input : array[] = [7, 42, 5, 6, 42, 8, 7, 5, 3, 6, 7]
//      X = 7 Y = 42
//Output : 9
//The longest prefix with same number of occurrences 
//of 7 and 42 is:
//7, 42, 5, 6, 42, 8, 7, 5, 3, 6 42

//https://www.geeksforgeeks.org/longest-prefix-contains-number-x-y-array/

public class MS_Longest_prefix {
	// Function to find the max index
	// of largest prefix with same
	// number if X and Y
	static int findIndex(int[] arr, int X, int Y, int n) {

		// counters for X and Y
		int nx = 0, ny = 0;

		int result = -1;
		for (int i = 0; i < n; i++) {
			// If value is equal to X
			// increment counter of X
			if (arr[i] == X)
				nx++;

			// If value is equal to Y
			// increment counter of Y
			if (arr[i] == Y)
				ny++;

			// If counters are equal(but not
			// zero) save the result as i
			if ((nx != 0) && (nx == ny))
				result = i;
		}

		return result;
	}

	// Driver code
	static public void main(String[] args) {
		int[] arr = { 7, 42, 5, 6, 42, 8, 7, 5, 3, 6, 7 };
		int X = 7, Y = 42;
		int n = arr.length;
		System.out.println("Ending index of prefix is " + findIndex(arr, X, Y, n));
	}

	// This code is contributed by vt_m.
	// Improved by Qu Dongfang.
}
