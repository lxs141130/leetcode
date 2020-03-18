package microsoft.OTAs;

/**
 * @author franksun
 * 
 * Feb 12, 2020
 * 
 */
//https://leetcode.com/problems/arithmetic-slices/


//time O(N)
//space O(1)
public class MS_Particle_Velocity {
	public static int numberOfArithmeticSlices(int[] A) {
        int dp = 0;
        int sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp = 1 + dp;
                sum += dp;
            } else {
                dp = 0;
            }
        }
        return sum;
    }
	
	public static void main(String[] args) {
		//5
		System.out.println(numberOfArithmeticSlices(new int[]{-1,1,3,3,3,2,3,2,1,0}));
		//6
		System.out.println(numberOfArithmeticSlices(new int[]{1,3,5,7,9}));
		//3
		System.out.println(numberOfArithmeticSlices(new int[]{7,7,7,7}));
		//3
		System.out.println(numberOfArithmeticSlices(new int[]{3,-1,-5,-9}));
		//0
		System.out.println(numberOfArithmeticSlices(new int[]{1,1,2,5,7}));
		//0
		System.out.println(numberOfArithmeticSlices(new int[]{3,-1}));
	}
}
