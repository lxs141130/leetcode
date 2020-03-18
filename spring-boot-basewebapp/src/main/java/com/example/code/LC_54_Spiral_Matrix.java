package com.example.code;

import java.util.ArrayList;
import java.util.List;

public class LC_54_Spiral_Matrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> rst = new ArrayList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return rst;
		}
		int m = matrix.length;
		int n = matrix[0].length;

		int x = 0;
		int y = 0;

		while (m > 0 && n > 0) {
			
			if (m == 1) {
				for (int i=0; i < n; i++) {
					rst.add(matrix[x][y++]);
				}
				break;
			} else if (n == 1) {
				for (int i = 0; i < m; i++) {
					rst.add(matrix[x++][y]);
				}
				break;
			}
			
			for (int i = 0; i < n - 1 ; i++) {
				rst.add(matrix[x][y++]);
			}
			
			for (int i = 0; i < m - 1 ; i++) {
				rst.add(matrix[x++][y]);
			}
			
			for (int i = n - 1; i > 0 ; i--) {
				rst.add(matrix[x][y--]);
			}
			
			for (int i = m - 1; i > 0 ; i--) {
				rst.add(matrix[x--][y]);
			}
			m -= 2;
			n -= 2;
			x++;
			y++;
		}
		return rst;
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		LC_54_Spiral_Matrix sm = new LC_54_Spiral_Matrix();
		System.out.print(sm.spiralOrder(matrix));
	}
}
