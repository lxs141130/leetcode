package indeed;

import java.util.Arrays;
import java.util.Stack;

public class LC_85_Maximal_Rectangle {
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		//make sure it can calculate area as the last extra column is always 0
		int[][] heights = new int[row][col + 1];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == '1') {
					heights[i][j] = i != 0 ? heights[i - 1][j] + 1 : 1;
				} else {
					heights[i][j] = 0;
				}
			}
		}
		int ans = 0;
		for (int[] height : heights) {
			ans = Math.max(ans, calculateArea(height));
		}

		return ans;
	}

	public int calculateArea(int[] height) {
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		int i = 0;
		while (i < height.length) {
			if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
				stack.push(i++);
			} else {
				int top = stack.pop();
				int len = !stack.isEmpty() ? i - stack.peek() - 1 : i;
				maxArea = Math.max(maxArea, len * height[top]);
			}
		}
		return maxArea;
	}

	public int maximalRectangle1(char[][] matrix) {
		if (matrix.length == 0)
			return 0;
		int m = matrix.length;
		int n = matrix[0].length;

		int[] left = new int[n]; // initialize left as the leftmost boundary possible
		int[] right = new int[n];
		int[] height = new int[n];

		Arrays.fill(right, n); // initialize right as the rightmost boundary possible

		int maxarea = 0;
		for (int i = 0; i < m; i++) {
			int cur_left = 0, cur_right = n;
			// update height
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == '1')
					height[j]++;
				else
					height[j] = 0;
			}
			// update left
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == '1')
					left[j] = Math.max(left[j], cur_left);
				else {
					left[j] = 0;
					cur_left = j + 1;
				}
			}
			// update right
			for (int j = n - 1; j >= 0; j--) {
				if (matrix[i][j] == '1')
					right[j] = Math.min(right[j], cur_right);
				else {
					right[j] = n;
					cur_right = j;
				}
			}
			// update area
			for (int j = 0; j < n; j++) {
				maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
			}
		}
		return maxarea;
	}

	public static void main(String[] args) {
		LC_85_Maximal_Rectangle mr = new LC_85_Maximal_Rectangle();
		char[][] matrix = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '0' } };
		System.out.println(mr.maximalRectangle(matrix));
	}
}
