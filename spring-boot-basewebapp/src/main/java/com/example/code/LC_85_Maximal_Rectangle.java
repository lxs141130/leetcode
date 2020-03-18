package com.example.code;

import java.util.Arrays;
import java.util.Stack;

public class LC_85_Maximal_Rectangle {
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		// one more col for end the calculation, will always be 0
		int[][] height = new int[row][col + 1];

		height = getHeights(matrix);
		int maxArea = 0;

		for (int i = 0; i < row; i++) {
			int area = getArea(height[i]);
			if (area > maxArea) {
				maxArea = area;
			}
		}

		return maxArea;
	}

	private int[][] getHeights(char[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] heights = new int[row][col + 1];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == '0') {
					heights[i][j] = 0;
				} else {
					heights[i][j] = i == 0 ? 1 : heights[i - 1][j] + 1;
				}
			}
		}
		return heights;
	}

	private int getArea(int[] height) {
		Stack<Integer> stack = new Stack<>();

		int i = 0;
		int maxArea = 0;

		while (i < height.length) {
			if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
				stack.push(i++);
			} else {
				int index = stack.pop();
				maxArea = Math.max(maxArea, height[index] * (stack.isEmpty() ? i : i - stack.peek() - 1));
			}
		}
		return maxArea;
	}
	
    public int maximalRectangle1(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[] left = new int[n]; // initialize left as the leftmost boundary possible
        int[] right = new int[n];
        int[] height = new int[n];

        Arrays.fill(right, n); // initialize right as the rightmost boundary possible

        int maxarea = 0;
        for(int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            // update height
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            // update left
            for(int j=0; j<n; j++) {
                if(matrix[i][j]=='1') left[j]=Math.max(left[j],cur_left);
                else {left[j]=0; cur_left=j+1;}
            }
            // update right
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                else {right[j] = n; cur_right = j;}    
            }
            // update area
            for(int j = 0; j < n; j++) {
                maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxarea;
    }

	public static void main(String[] args) {
		LC_85_Maximal_Rectangle mr = new LC_85_Maximal_Rectangle();
		char[][] matrix = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '0' } };
		System.out.println(mr.maximalRectangle1(matrix));
	}
}
