package microsoft.OTAs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author franksun
 * 
 * Feb 13, 2020
 * 
 */
//https://leetcode.com/discuss/interview-question/492652/
//the basic idea is to know the following.
//	1.If the row doesn't have any reserved seats, then you can put 2 families (in B to E, and F to J).
//	2.If row has reserved seats and those reserved seats are between B and J, it will be able to hold either 1 family, or no family at all depending on where reserved seats are. Notice A and K being reserved doesn't effect the possible four-person family seating.
//Base on that, you can figure out the maximum number of four-person families that can be seated for each row, and add them all up in O(N).

//time -> O(N)
//space -> O(N)
public class MS_Plane_Seat_Reservation {
	public static int seatReserve(int N, String s) {
		Map<Integer, Set<Character>> map = new HashMap<>();
		String[] strArr = s.split("\\s");
		for (String str : strArr) {
			int row = str.charAt(0) - '0';
			char c = str.charAt(1);
			Set<Character> set = map.getOrDefault(row, new HashSet<>());
			set.add(c);
			map.put(row, set);
		}
		
		int rst = N * 2;
		
		for (int i = 1; i <= N; i++) {
			Set<Character> set =  map.get(i);
			if (set.contains('B') || set.contains('C') || set.contains('D') || set.contains('E')) {
				rst--;
			}
			if (set.contains('F') || set.contains('G') || set.contains('H') || set.contains('J')) {
				rst--;
			}
		}
		
		return rst;
	}
	
	public static void main(String[] args) {
		//4
		System.out.println(seatReserve(2,"1A 2K"));
		
		//3
		System.out.println(seatReserve(2,"1A 2K 1C"));
		
		//2
		System.out.println(seatReserve(2,"1A 2F 1C"));
		
		//1
		System.out.println(seatReserve(2,"1A 2F 1C 1G"));
		
		//0
		System.out.println(seatReserve(2,"1A 2F 1C 1G 2C"));
	}
}
