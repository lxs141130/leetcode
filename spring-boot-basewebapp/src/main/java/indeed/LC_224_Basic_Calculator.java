/**
 * 
 */
package indeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author franksun
 *
 */
public class LC_224_Basic_Calculator {

	public int calculate(String s) {
	    // delete white spaces
		s = String.join("", s.split("\\s"));
 
	    Stack<String> stack = new Stack<String>();
	    char[] arr = s.toCharArray();
 
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < arr.length; i++) {
		    if (arr[i] >= '0' && arr[i] <= '9') {
			    sb.append(arr[i]);
 
			    if (i == arr.length - 1) {
				    stack.push(sb.toString());
			    }
		    } else {
			    if (sb.length() > 0) {
				    stack.push(sb.toString());
				    sb = new StringBuilder();
			    }
 
			    if (arr[i] != ')') {
				    stack.push(arr[i]+"");
			    } else {
				    // when meet ')', pop and calculate
				    ArrayList<String> t = new ArrayList<String>();
				    while (!stack.isEmpty()) {
					    String top = stack.pop();
					    if (top.equals("(")) {
						    break;
					    } else {
						    t.add(0, top);
					    }
				    }
 
				    int temp = 0;
				    if (t.size() == 1) {
					    temp = Integer.valueOf(t.get(0));
				    } else {
					    temp = calculatePartition(t);
			    	}
				    stack.push(String.valueOf(temp));
		    	}
	    	}
	    }
 
	    List<String> t = new ArrayList<String>();
	    while (!stack.isEmpty()) {
		    String elem = stack.pop();
	    	t.add(0, elem);
    	}
	    
	    int temp = calculatePartition(t);
    	return temp;
    }

	private int calculatePartition(List<String> t) {
		int temp = 0;
		for (int i = t.size() - 1; i > 0; i = i - 2) {
    		if (t.get(i - 1).equals("-")) {
    			temp += 0 - Integer.valueOf(t.get(i));
    		} else {
    			temp += Integer.valueOf(t.get(i));
    		}
    	}
    	temp += Integer.valueOf(t.get(0));
		return temp;
	}

	public static void main(String[] args) {
		LC_224_Basic_Calculator bc = new LC_224_Basic_Calculator();
		String s = " ( ( 4   -5 +2 )-3) ";
		System.out.print(bc.calculate(s));
	}
}
