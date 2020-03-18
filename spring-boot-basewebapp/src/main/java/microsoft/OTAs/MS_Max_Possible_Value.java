package microsoft.OTAs;

/**
 * @author franksun
 * 
 *         Feb 11, 2020
 * 
 */
//time -> O(N), N is the number of digits for input 
//space -> O(N), N is the number of digits for input
public class MS_Max_Possible_Value {
	public int MaximumPossibleValue(int N) throws Exception {
		if (N < -8000 || N > 8000) {
			throw new Exception ("Invalid input");
		}
		if (N == 0) {
			return 50;
		}
		StringBuilder num = new StringBuilder(Math.abs(N) + "");
		for (int i = 0; i < num.length(); i++) {
			if ((N > 0 && num.charAt(i) < (char) (5 + '0')) || (N < 0 && num.charAt(i) > (char) (5 + '0'))) {
				num.insert(i, "5");
				return N > 0 ? Integer.parseInt(num.toString()) : -1 * Integer.parseInt(num.toString());
			}
		}
		num.append("5");
		return N > 0 ? Integer.parseInt(num.toString()) : -1 * Integer.parseInt(num.toString());

	}

	public static void main(String[] args) throws Exception {
		MS_Max_Possible_Value mp = new MS_Max_Possible_Value();
		//5234
		System.out.println(mp.MaximumPossibleValue(234));
		//6785
		System.out.println(mp.MaximumPossibleValue(678));
		//50
		System.out.println(mp.MaximumPossibleValue(0));
		//5268;
		System.out.println(mp.MaximumPossibleValue(268));
		//-2345;
		System.out.println(mp.MaximumPossibleValue(-234));
		//-5678
		System.out.println(mp.MaximumPossibleValue(-678));
		//-2568;
		System.out.println(mp.MaximumPossibleValue(-268));
		int N8 = -60000;
		System.out.println(mp.MaximumPossibleValue(N8));
	}
}
