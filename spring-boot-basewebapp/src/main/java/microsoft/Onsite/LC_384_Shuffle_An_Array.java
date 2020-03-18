package microsoft.Onsite;

import java.util.Random;

/**
 * @author franksun
 * 
 * Feb 19, 2020
 * 
 */
public class LC_384_Shuffle_An_Array {
	Random rand = new Random();
    int[] originalNum;
    int[] arrays;
	
    public LC_384_Shuffle_An_Array(int[] nums) {
        originalNum = nums;
        arrays = originalNum.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
    	arrays = originalNum;
    	originalNum = originalNum.clone();
        return originalNum;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	for (int i = 0; i < arrays.length; i++) {
			int randomIndexToSwap = rand.nextInt(arrays.length);
			int temp = arrays[randomIndexToSwap];
			arrays[randomIndexToSwap] = arrays[i];
			arrays[i] = temp;
		}
		
		return arrays;
    }

	public static void main(String[] args) {
		int[] numsToRotate = {1,2,3};
		LC_384_Shuffle_An_Array sa = new LC_384_Shuffle_An_Array(numsToRotate);
		
		
		System.out.println(sa.shuffle());
		
		System.out.println(sa.shuffle());
		
		System.out.println(sa.shuffle());
		
		System.out.println(sa.shuffle());
			
		System.out.println(sa.reset());
	}
}
