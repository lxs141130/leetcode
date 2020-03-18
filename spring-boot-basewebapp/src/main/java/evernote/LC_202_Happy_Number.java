package evernote;

import java.util.HashSet;
import java.util.Set;

/**
 * @author franksun
 * 
 * Feb 22, 2020
 * 
 */
public class LC_202_Happy_Number {
	public boolean isHappy(int n) {
		if (n <= 0) {
			return false;
		}
		Set<Integer> set = new HashSet<>();
		while (n != 1 && !set.contains(n)) {
			set.add(n);
	        n = getNext(n);
		}
		return n == 1;
	}
	
	
	public boolean isHappy1(int n) {
		int slowNext = n;
		int fastNext = getNext(n);
		while (fastNext != 1 && slowNext != fastNext) {
			slowNext = getNext(slowNext);
			fastNext = getNext(getNext(fastNext));
		}
		return fastNext == 1;
	}
	
	private int getNext(int n) {
		int nextNum = 0;
		while (n != 0) {
			int digit = n % 10;
			n /= 10;
			nextNum+= digit * digit;
		}
		return nextNum;
	}
	
	
	public static void main(String[] args) {
		LC_202_Happy_Number hn = new LC_202_Happy_Number();
		System.out.println(hn.isHappy(19));
		System.out.println(hn.isHappy1(19));
	}
}
