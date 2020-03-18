package microsoft.Onsite;

import java.math.BigInteger;

/**
 * @author franksun
 * 
 *         Feb 25, 2020
 * 
 */
public class Geeks_next_Greatest {
	public void nextGreatest(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		if (arr.length == 1) {
			arr[0] = -1;
			return;
		}
		int len = arr.length;
		int max = arr[len - 1];
		arr[len - 1] = -1;
		for (int i = len - 2; i >= 0; i--) {
			int temp = arr[i];
			arr[i] = max;
			if (temp > max) {
				max = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 16, 17, 4, 3, 5, 2 };
		Geeks_next_Greatest ng = new Geeks_next_Greatest();
		ng.nextGreatest(arr);
		System.out.println(arr);
		
		BigInteger b1 = new BigInteger("11111");
		BigInteger b2 = new BigInteger("222444444444444444444444444444444444444222");
		BigInteger sum = b1.add(b2);
		System.out.print(sum);
	}
}
