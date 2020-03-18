package microsoft.OTAs;

/**
 * @author franksun
 * 
 * Feb 12, 2020
 * 
 */

//time -> O(N)
//space -> O(1)
public class MS_Min_Deletions_To_Obtain_String_in_Right_Format {
	public static int minDeletion (String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		//rst[0] -> how many B
		//rst[1] -> how many A after first B
		int rst[] = new int[] {0,0};
		//index of first B
		int index = -1;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'A') {
				if (index != -1) {
					rst[1]++;
				}
			} else {
				index = index == -1 ? i : index;
				rst[0]++;
			}
		}
		//rst is the min value of 
			//1. remove all B
			//2. remove all A after first B
		return Math.min(rst[0], rst[1]);
	}
	
	public static void main(String[] args) {
		//0
		System.out.println(minDeletion("AAAA"));
		//0
		System.out.println(minDeletion("BBBB"));
		//0
		System.out.println(minDeletion("AABB"));
		//2
		System.out.println(minDeletion("BBAA"));
		//3
		System.out.println(minDeletion("AABABABAA"));
	}
}
