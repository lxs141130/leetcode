package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Mar 5, 2020
 * 
 */
public class LC_36_Valid_Sudoku {
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.' && !isValid(board, i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValid(char[][] board, int row, int col) {
		// invalid boundaries
		if (row < 0 || row > board.length || col < 0 || col > board[0].length) {
			return false;
		}

		// check row
		for (int j = 0; j < board[0].length; j++) {
			if (j != col && board[row][j] == board[row][col]) {
				return false;
			}
		}

		// check col
		for (int i = 0; i < board.length; i++) {
			if (i != row && board[i][col] == board[row][col]) {
				return false;
			}
		}

		// check cell
		int m = row / 3;
		int n = col / 3;
		for (int i = m * 3; i < m * 3 + 3; i++) {
			for (int j = n * 3; j < n * 3 + 3; j++) {
				if (i != row && j != col && board[i][j] == board[row][col]) {
					return false;
				}
			}
		}

		return true;
	}
}
