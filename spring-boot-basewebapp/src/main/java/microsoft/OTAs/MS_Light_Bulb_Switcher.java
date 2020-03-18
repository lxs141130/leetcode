package microsoft.OTAs;

/**
 * @author franksun
 * 
 * Feb 13, 2020
 * 
 */
public class MS_Light_Bulb_Switcher {
	
//For any bulb to shine at position k (not array index, but the actual position in the line), 
//	all the bulbs 1 through k must be turned on at a given instance. 
//	Now, if A[i] = p such that it makes all the bulbs 1 through k to shine, then p must be in the range [1, k-1], 
//	inclusive and no other position in that range should be missing.
//This means that the sum of the positions of the turned on bulbs must be 1+2+3+...+k. 
//	This sum also has to be equal to 1+2+3+ ... +i (1 based index) because no other bulb 
//	after position k must not be turned on according to given condition. 
	
	
//	Time O(N), Space O(1). Code below:
	public static int getShiningCounts(int[] arr) {
		int ans = 0, sum = 0, target = 0;
			
		for(int i = 1; i<= arr.length; i++) {
			sum += arr[i-1];
			target += i ; //Sum from 1 to i
			if(sum == target) ans++;
		}
			
		return ans;
	}
	
	
	public static int bulbShines(int[] A) {
		if (A == null || A.length < 0) {
			return 0;
		}
		int rst = 0;
		int len = A.length;
		boolean[] on = new boolean[len + 1];
		boolean[] shine = new boolean[len + 1];
		on[0] = true;
		shine[0] = true;
		
		for (int i = 0 ;i < len; i++) {
			on[A[i]] = true;
			shine[A[i]] = true;
			for (int j = 1; j < A[i]; j++) {
				if (shine[j]) {
					continue;
				} else {
					if (shine[j - 1] && on[j]) {
						shine[j] = true;
					} else {
						shine[A[i]] = false;
						break;
					}
				}
			}
			if (shine[A[i]]) {
				shineBulbsAfter(on, shine, A[i] + 1);
				if(allTheLightOnAreShine(on, shine)) {
					rst++;
				}
			}

		}
		return rst;
	}
	
	/**
	 * @param on
	 * @param shine
	 * @param i
	 * @return void
	 * @author franksun
	 */
	private static void shineBulbsAfter(boolean[] on, boolean[] shine, int i) {
		if (i > on.length - 1) {
			return;
		}
		for (int j = i; j < on.length; j++) {
			if (shine[j - 1] && on[j]) {
				shine[j] = true;
			} else {
				return;
			}
		}
		
	}

	/**
	 * @param on
	 * @param shine
	 * @return
	 * @return boolean
	 * @author franksun
	 */
	private static boolean allTheLightOnAreShine(boolean[] on, boolean[] shine) {
		int onBulbs = 0;
		int shineBulbs = 0;
		for (int i = 1 ; i < on.length; i++) {
			if (on[i]) {
				onBulbs++;
			}
			if (shine[i]) {
				shineBulbs++;
			}
		}
		return onBulbs > 0 && shineBulbs == onBulbs;
	}

	public static void main(String[] args) {
		//3
		System.out.println(bulbShines(new int[] {2,1,3,5,4}));
		System.out.println(getShiningCounts(new int[] {2,1,3,5,4}));
		//2
		System.out.println(bulbShines(new int[] {2,3,4,1,5}));
		System.out.println(getShiningCounts(new int[] {2,3,4,1,5}));
		//1
		System.out.println(bulbShines(new int[] {5,4,3,2,1}));
		System.out.println(getShiningCounts(new int[] {5,4,3,2,1}));
	}
}
