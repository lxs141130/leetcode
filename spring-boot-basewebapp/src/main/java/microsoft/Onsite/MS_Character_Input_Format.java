package microsoft.Onsite;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author franksun
 * 
 *         Mar 2, 2020
 * 
 */
public class MS_Character_Input_Format {
	public char[] formatCharArray(char[] array) {
		int len = array.length;
		Stack<Character> stack = new Stack<>();
		boolean isShift = false;
		int i = 0;
		while (i < len) {
			while (i < len && (Character.isAlphabetic((array[i])) || Character.isDigit(array[i]))) {
				char ch = Character.isDigit(array[i]) ? array[i]
						: isShift ? Character.toUpperCase(array[i]) : Character.toLowerCase(array[i]);
				stack.push(ch);
				i++;
			}
			if (i < len && array[i] == '$') {
				isShift = !isShift;
				i++;
			}
			if (i < len && array[i] == '#') {
				if (!stack.isEmpty()) {
					stack.pop();
				}
				i++;
			}
		}
		char[] res = new char[stack.size()];
		int index = stack.size() - 1;
		while (!stack.isEmpty()) {
			res[index--] = stack.pop();
		}
		return res;
	}

	public char[] formatCharArray1(char[] array) {
		int len = array.length;
		boolean isShift = false;
		int i = 0;
		int j = 0;
		while (j < len) {
			if(i == j && j < len && (Character.isAlphabetic((array[i])) || Character.isDigit(array[i]))){
				while (i == j && j < len && (Character.isAlphabetic((array[i])) || Character.isDigit(array[i]))) {
					i++;
					j++;
				}
			}  
			if (j < len && array[j] == '$') {
				isShift = !isShift;
				j++;
			}
			 
			if (j < len && array[j] == '#') {
				if (i > 0) {
					i--;
				}
				j++;
			}
			 
			if (j < len && Character.isAlphabetic((array[j]))) {
				char ch = Character.isDigit(array[j]) ? array[j]
						: isShift ? Character.toUpperCase(array[j]) : Character.toLowerCase(array[j]);
				array[j] = ch;
				swap(i, j, array);
				i++;
				j++;
			}
		}
		
		return Arrays.copyOfRange(array, 0, i);
	}

	private void swap(int i, int j, char[] arr) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		MS_Character_Input_Format cif = new MS_Character_Input_Format();

		System.out.println(cif.formatCharArray1(new char[] { 'a', 'B', '1', '3', '$', 'a', 'b', '#' }));

		System.out.println(cif.formatCharArray1(new char[] { 'a', '$', '#', 'c' }));
		
		
	}
}
