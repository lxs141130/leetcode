package microsoft.OTAs;

/**
 * @author franksun
 * 
 * Feb 12, 2020
 * 
 */

//https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/

//time -> O (n)
//space -> O(n)
public class MS_N_Unique_Integers_Sum_Up_To_Zero {
	public static int[] sumZero(int n) {
        if (n == 0) {
            return new int[0];
        }
        if (n == 1) {
            return new int[]{0};
        }
        int[] rst = new int[n];
        int i = 0;
        int num = n / 2;
        if (n %2 == 1) {
            rst[i++] = 0;
        }
        while (i < n) {
            if (num == 0) {
                num--;
                continue;
            }
            rst[i++] = num--;
        }
        return rst;
    }
	
	public static void main(String[] args) {
		System.out.println(sumZero(5));
		System.out.println(sumZero(4));
		System.out.println(sumZero(3));
		System.out.println(sumZero(1));
		System.out.println(sumZero(0));
	}
}
