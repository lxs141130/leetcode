package microsoft.Onsite;

/**
 * @author franksun
 * 
 * Feb 18, 2020
 * 
 */
public class LC_529_Minesweeper {
	public static char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        solve(board, click[0], click[1]);
        return board;
	}

	static int[] dx = {-1, 0, 1, -1, 1, 0, 1, -1};
    static int[] dy = {-1, 1, 1, 0, -1, -1, 0, 1};
    
	private static void solve(char[][] board, int x, int y) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length 
				|| board[x][y] != 'E') {
			return;
		}
		int num = getNumsOfBombs(board, x, y);
		if (num == 0) {
            board[x][y] = 'B';
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                solve(board, nx, ny);
            }
        } else {
            board[x][y] = (char)('0' + num);
        }
		
	}

	private static int getNumsOfBombs(char[][] board, int x, int y) {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) {
                continue;	
            }
            if (board[nx][ny] == 'M' || board[nx][ny] == 'X') {
                num++;
            }
        }
        return num;
    }
	
	public static void main(String[] args) {
		System.out.println(updateBoard(new char[][] {{'E', 'E', 'E', 'E', 'E'},{'E', 'E', 'M', 'E', 'E'},{'E', 'E', 'E', 'E', 'E'},{'E', 'E', 'E', 'E', 'E'}}, new int[] {3, 0}));
	}
	
	
}
