package com.example.code;

public class LC_48_Rotate_Image {
	public void rotate(int[][] matrix) {
	    int n = matrix.length;

	    // transpose matrix
	    for (int i = 0; i < n; i++) {
	      for (int j = i; j < n; j++) {
	        int tmp = matrix[j][i];
	        matrix[j][i] = matrix[i][j];
	        matrix[i][j] = tmp;
	      }
	    }
	    // reverse each row
	    for (int i = 0; i < n; i++) {
	      for (int j = 0; j < n / 2; j++) {
	        int tmp = matrix[i][j];
	        matrix[i][j] = matrix[i][n - j - 1];
	        matrix[i][n - j - 1] = tmp;
	      }
	    }
	  }
	
	public static void main(String[] args) {
		LC_48_Rotate_Image ri = new LC_48_Rotate_Image();
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		ri.rotate(matrix);
		System.out.print(matrix);
	}
}
