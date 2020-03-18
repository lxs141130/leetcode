package microsoft.Onsite;

import java.util.Scanner;

/**
 * @author franksun
 * 
 *         Feb 21, 2020
 * 
 */
public class LC_277_Find_The_Celebrity {
	int[][] graph;
	
	public int findCelebrity(int n) {
        int candidate = 0;
        //candidate is the one other people knows
        //for example one
        //0 -> 0, 0 is celebrity
        //0 -> 1, 1 is celebrity
        //1 -/-> 2, 1 is celebrity
        for(int i = 0; i < n; i++) {
            if(knows(candidate,i)) {
                candidate = i;
            }
        }
        //make sure celebrity do not know the people who know him, index before him, 
        //index after him is stopped in first for loop
        //for checking candidate for celebrity
        for(int i = 0; i < candidate; i++) {
            if(knows(candidate,i)) {
                return -1;
            }
        }
        //make sure everyone know candidate 
        //then he is the celebrity
        for(int i = 0; i < n; i++){
            if(!knows(i,candidate)){
                return -1;
            }
        }
        return candidate;
    }

	private boolean knows(int i, int candidate) {
		if (graph[i][candidate] == 1) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
//		LC_277_Find_The_Celebrity fc = new LC_277_Find_The_Celebrity();
//		fc.graph = new int[][] {{1,1,0},{0,1,0},{1,1,1}};
//		System.out.print(fc.findCelebrity(3));
		
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int col = sc.nextInt();
		
		int[][] matrix = new int[row][col];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		
		sc.close();
		
		System.out.println(matrix);
		
		
		
	}
}
