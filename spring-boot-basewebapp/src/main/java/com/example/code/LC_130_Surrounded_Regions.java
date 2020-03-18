package com.example.code;

import java.util.LinkedList;
import java.util.Queue;

public class LC_130_Surrounded_Regions {
	
	int[] dr = {0,1,0,-1};
	int[] dc = {-1,0,1,0};
	
	public void solve(char[][] board) {
		if (board == null || board[0].length == 0 || board.length == 0) {
			return;
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0 ; i < board.length; i ++) {
			for (int j = 0; j < board[0].length; j++) {
				if ((i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) && board[i][j] == 'O') {
					board[i][j] = '*';
					q.offer(i*board[0].length + j);
					bfs(board, q);
				}
			}
		}
		
		for (int i = 0 ; i < board.length; i ++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = board[i][j] == '*' ? 'O' : 'X';
			}
		}
	}

	private void bfs(char[][] board, Queue<Integer> q) {
		// TODO Auto-generated method stub
		while  (!q.isEmpty()) {
			int index = q.poll();
			int rowIndex = index / board[0].length;
			int colIndex = index % board[0].length;
			for (int i = 0; i < 4; i++) {
				int x = rowIndex + dr[i];
				int y = colIndex + dc[i];
				if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
					board[x][y] = '*';
					q.offer(x * board[0].length + y);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		LC_130_Surrounded_Regions sr = new LC_130_Surrounded_Regions();
		char[][] board1  = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
		sr.solve(board1);
		printArray(board1);
		
		System.out.println();
		
		char[][] board2  = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','X','O','X'}};
		sr.solve(board2);
		printArray(board2);
	}
	
	private static void printArray(char[][] arr) {
		// TODO Auto-generated method stub
		int row = arr.length;
		int col = arr[0].length;
		for (int i = 0 ; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}
