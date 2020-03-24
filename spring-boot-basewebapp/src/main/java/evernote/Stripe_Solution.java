// package evernote;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// /* 
// Your previous Markdown content is preserved below:

// This is just a simple shared plaintext pad, with no execution capabilities.

// When you know what language you'd like to use for your interview,
// simply choose it from the dropdown in the top bar.

// You can also change the default language your pads are created with
// in your account settings: https://coderpad.io/settings

// Enjoy your interview!
// import java.io.*;
// import java.util.*;

// /*
//  * To execute Java, please define "static void main" on a class
//  * named Solution.
//  *
//  * If you need more classes, simply define them in-line.
//  */

// public class Stripe_Solution {
// //   [ {"a": 1, "b": 0, "c": 0, "d": 0},
// //   {"a": 0, "b": 2, "c": 3, "d": 0},
// //   {"a": 0, "b": 0, "c": 0, "d": 4}, ]
// 	public List<Row> searchColumMin(List<Row> request, String colName) {
// 		List<Row> res = new ArrayList<Row>();
// 		// {"a": 0, "b": 0, "c": 0, "d": 0}
// 		if (request == null || request.size() == 0) {
// 			return res;
// 		}
// 		int minVal = Integer.MAX_VALUE;

		
// 		if (colName.equalsIgnoreCase("col1")) {
// 			for (Row row : request) {
// 				int colVal = row.getCol1();
// 				if (colVal < minVal) {
// 					minVal = colVal;
// 				}
// 			}
			
// 			for (Row row : request) {
// 				int colVal = row.getCol1();
// 				if (colVal == minVal) {
// 					res.add(row);
// 				}
// 			}
// 		} else if (colName.equalsIgnoreCase("col2")) {
// 			for (Row row : request) {
// 				int colVal = row.getCol2();
// 				if (colVal < minVal) {
// 					minVal = colVal;
// 				}
// 			}
			
// 			for (Row row : request) {
// 				int colVal = row.getCol2();
// 				if (colVal == minVal) {
// 					res.add(row);
// 				}
// 			}
// 		} else if (colName.equalsIgnoreCase("col3")) {
// 			for (Row row : request) {
// 				int colVal = row.getCol3();
// 				if (colVal < minVal) {
// 					minVal = colVal;
// 				}
// 			}
			
// 			for (Row row : request) {
// 				int colVal = row.getCol3();
// 				if (colVal == minVal) {
// 					res.add(row);
// 				}
// 			}
// 		} else {
// 			for (Row row : request) {
// 				int colVal = row.getCol4();
// 				if (colVal < minVal) {
// 					minVal = colVal;
// 				}
// 			}
			
// 			for (Row row : request) {
// 				int colVal = row.getCol4();
// 				if (colVal == minVal) {
// 					res.add(row);
// 				}
// 			}
// 		}

// 		return res;
// 	}

// 	public static void main(String[] args) {
// 		Stripe_Solution solution = new Stripe_Solution();
// 		List<Row> request = new ArrayList<>();
// 		Row row1 = new Row();
// 		row1.setCol1(1);
// 		row1.setCol2(-2);
// 		Row row2 = new Row();
// 		row2.setCol1(3);
// 		request.addAll(Arrays.asList(row1, row2));

// 		String colName = "col2";

// 		List<Row> res = solution.searchColumMin(request, colName);
// 		for (Row r : res) {
// 			System.out.println(r.toString());
// 		}

// 	}
// }

// class Row {
// 	int col1;
// 	int col2;
// 	int col3;
// 	int col4;

// 	public Row() {

// 	}

// 	public int getCol1() {
// 		return col1;
// 	}

// 	public void setCol1(int col1) {
// 		this.col1 = col1;
// 	}

// 	public int getCol2() {
// 		return col2;
// 	}

// 	public void setCol2(int col2) {
// 		this.col2 = col2;
// 	}

// 	public int getCol3() {
// 		return col3;
// 	}

// 	public void setCol3(int col3) {
// 		this.col3 = col3;
// 	}

// 	public int getCol4() {
// 		return col4;
// 	}

// 	public void setCol4(int col4) {
// 		this.col4 = col4;
// 	}

// 	public Row(int col1, int col2, int col3, int col4) {
// 		super();
// 		this.col1 = col1;
// 		this.col2 = col2;
// 		this.col3 = col3;
// 		this.col4 = col4;
// 	}

// 	@Override
// 	public String toString() {
// 		return "Row [col1=" + col1 + ", col2=" + col2 + ", col3=" + col3 + ", col4=" + col4 + "]";
// 	}
// }

// /*
//  * Your previous Plain Text content is preserved below:
//  * 
//  * For this interview, imagine that we are working with a simple database. Each
//  * row associates column names (strings) with integer values (for example: 5, 0,
//  * -3, and so on). Here's a table with three rows:
//  * 
//  * a b c d --------------- 1 0 0 0 0 2 3 0 0 0 0 4
//  * 
//  * We can choose to represent a database table in JSON as an array of objects.
//  * For example, the previous table could be written as:
//  * 
//  * [ {"a": 1, "b": 0, "c": 0, "d": 0}, {"a": 0, "b": 2, "c": 3, "d": 0}, {"a":
//  * 0, "b": 0, "c": 0, "d": 4} ]
//  * 
//  * (There is no need to use JSON in your solutions -- the notation is just used
//  * to introduce and explain the problems.)
//  * 
//  * Write a function minByColumn that takes a database table (as above), along
//  * with a column, and returns the row that contains the minimum value for the
//  * given column. If a row doesn't have any value for the column, your function
//  * should behave as though the value for that column was zero.
//  * 
//  * In addition to writing this function, you should use tests to demonstrate
//  * that it's correct, either via an existing testing system or one you create.
//  * 
//  * ## Examples
//  * 
//  * table1 = [ {"a": 1}, {"a": 2}, {"a": 3} ] minByColumn(table1, "a") returns
//  * {"a": 1}
//  * 
//  * table2 = [ {"a": 1, "b": 2}, {"a": 3, "b": 0} ] minByColumn(table2, "b")
//  * returns {"a": 3, "b": 0}
//  * 
//  * table3 = [ {"a": 1, "b": -2}, {"a": 3} ] minByColumn(table3, "b") returns
//  * {"a": 1, "b": -2}
//  * 
//  * # Part 2. Min-By-Order In part 1 you may have noticed that it's possible for
//  * two rows to be "tied", meaning that either would be an acceptable return
//  * value from minByColumn. Consider:
//  * 
//  * ``` table4 = [ {"a": 1, "b": 2}, {"a": 1, "b": 3}, {"a": 1, "b": 4} ]
//  * minByColumn(table4, "a") # returns ??? ```
//  * 
//  * Since all three rows have the same value for a, all three rows are acceptable
//  * candidates to be returned by `minByColumn(table, "a")`.
//  * 
//  * In these cases it would be nice if users could specify additional columns
//  * (e.g. b) to use as tie-breakers. A tie-breaker would only apply in cases
//  * where multiple rows share the same minimum values. In the previous example,
//  * the row `{"a": 1, "b": 2}` is tied for smallest a value (1) and of all the
//  * tied candidates, it has the smallest b value (2). If two records had equal
//  * values for a and also for b then another tie-breaker (e.g. c) could be used.
//  * When records are tied with respect to all columns, either record may be
//  * considered the minimum.
//  * 
//  * Write a method `minByOrder` that takes a database table and a list of
//  * columns, and returns the row with the minimum column values using the
//  * tie-breaking logic above.
//  * 
//  * If only one column is provided, then the behavior of minByOrder is identical
//  * to passing that column to `minByColumn`:
//  * 
//  * `minByOrder(table, [column])` is equal to `minByColumn(table, column)`
//  * 
//  * As in Part 1, you should use tests to demonstrate that it's correct, either
//  * via an existing testing system or one you create.
//  * 
//  * ## Examples Here are some examples provided in JSON + pseudocode:
//  * 
//  * ``` table5 = [ {"x": 1, "y": 3}, {"x": 1} ] minByOrder(table5, ["x", "y"]) #
//  * returns {"x": 1} ```
//  * 
//  * ``` table6 = [ {"x": 2, "y": 3}, {"x": 2, "y": 1}, {"x": 1, "y": 10} ]
//  * minByOrder(table6, ["x", "y"]) # returns {"x": 1, "y": 10} ```
//  * 
//  * ``` table7 = [ {"x": 3, "y": -1}, {"x": 1, "y": 10, "z": 1}, {"x": 1, "y":
//  * 10} ] minByOrder(table7, ["x", "y", "z"]) # returns {"x": 1, "y": 10} ```
//  * 
//  * ``` table8 = [ {"x": 1, "y": 2, "z": 3}, {"x": 2, "y": 2, "z": 2} ]
//  * minByOrder(table8, ["x", "y", "z"]) # returns {"x": 1, "y": 2, "z": 3} ```
//  * 
//  * 
//  */
