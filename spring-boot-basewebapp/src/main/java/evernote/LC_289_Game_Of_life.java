package evernote;

/**
 * @author franksun
 * 
 *         Mar 8, 2020
 * 
 */
public class LC_289_Game_Of_life {
	//这里给了两种方法，第一种是直接编码，编码规则是    
	//		0 : 上一轮是0，这一轮过后还是0
    //      1 : 上一轮是1，这一轮过后还是1
    //      2 : 上一轮是1，这一轮过后变为0
    //      3 : 上一轮是0，这一轮过后变为1
	
//这样，对于一个节点来说，如果它周边的点是1或者2，就说明那个点上一轮是活的。最后，在遍历一遍数组，把我们编码再解回去，即0和2都变回0，1和3都变回1，就行了。
//时间 O(NN) 空间 O(1)
	public void gameOfLife(int[][] board) {
		
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int lives = 0;
				// 判断上边
				if (i > 0) {
					lives += board[i - 1][j] == 1 || board[i - 1][j] == 2 ? 1 : 0;
				}
				// 判断左边
				if (j > 0) {
					lives += board[i][j - 1] == 1 || board[i][j - 1] == 2 ? 1 : 0;
				}
				// 判断下边
				if (i < m - 1) {
					lives += board[i + 1][j] == 1 || board[i + 1][j] == 2 ? 1 : 0;
				}
				// 判断右边
				if (j < n - 1) {
					lives += board[i][j + 1] == 1 || board[i][j + 1] == 2 ? 1 : 0;
				}
				// 判断左上角
				if (i > 0 && j > 0) {
					lives += board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 2 ? 1 : 0;
				}
				// 判断右下角
				if (i < m - 1 && j < n - 1) {
					lives += board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 2 ? 1 : 0;
				}
				// 判断右上角
				if (i > 0 && j < n - 1) {
					lives += board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 2 ? 1 : 0;
				}
				// 判断左下角
				if (i < m - 1 && j > 0) {
					lives += board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 2 ? 1 : 0;
				}
				// 根据周边存活数量更新当前点，结果是0和1的情况不用更新
				if (board[i][j] == 0 && lives == 3) {
					board[i][j] = 3;
				} else if (board[i][j] == 1) {
					if (lives < 2 || lives > 3)
						board[i][j] = 2;
				}
			}
		}
		// 解码
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = board[i][j] % 2;
			}
		}
	}

	public static void main(String[] args) {
		LC_289_Game_Of_life gol = new LC_289_Game_Of_life();
		gol.gameOfLife(new int[][] { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } });
	}
}
