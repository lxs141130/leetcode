package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Feb 24, 2020
 * 
 */
public class LC_79_Word_Serach {
	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0) {
			return false;
		}
		if (word == null || word.length() == 0) {
			return true;
		}
		int m = board.length;
		int n = board[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (helper(board, word, i, j, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, 1, 0, -1 };

	private boolean helper(char[][] board, String word, int i, int j, int index) {
		if (index == word.length()) {
			return true;
		}
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
			return false;
		}

		boolean existed = false;
		board[i][j] = '#';

		for (int k = 0; k < 4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];
			existed = helper(board, word, x, y, index + 1);
			if (existed) {
				break;
			}
		}

		board[i][j] = word.charAt(index);
		return existed;
	}

	public static void main(String[] args) {
		LC_79_Word_Serach ws = new LC_79_Word_Serach();
		char[][] board = new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		System.out.println(ws.exist(board, "ABCCED"));
		System.out.println(ws.exist(board, "SEE"));
		System.out.println(ws.exist(board, "ABCB"));
	}
}
