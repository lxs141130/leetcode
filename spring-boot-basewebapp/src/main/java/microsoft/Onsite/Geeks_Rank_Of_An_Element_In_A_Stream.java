package microsoft.Onsite;

/**
 * @author franksun
 * 
 * Feb 19, 2020
 * 
 */

//https://www.geeksforgeeks.org/rank-element-stream/

public class Geeks_Rank_Of_An_Element_In_A_Stream {

	
	//use BST
	class Node {
		int val;
		Node left;
		Node right;
		int leftSize;
		
		public Node(int val) {
			this.val = val;
			this.leftSize = 0;
		}
	}
	
	public int getRank(int[] array, int x)  {
		Node root = buildTree(array);
		return getRankInBST(root, x);
	}
	
	
	private int getRankInBST(Node root, int x) {
		// Step 1.  
	    if (root.val == x)  
	        return root.leftSize;  
	  
	    // Step 2.  
	    if (x < root.val) {  
	        if (root.left == null)  
	            return -1;  
	        else
	            return getRankInBST(root.left, x);  
	    }  
	  
	    // Step 3.  
	    else {  
	        if (root.right == null)  
	            return -1;  
	        else {  
	            int rightSize = getRankInBST(root.right, x);  
	            return root.leftSize + 1 + rightSize;  
	        }  
	    }  
	}


	private Node buildTree(int[] array) {
		Node root = null;
		for (int i = 0; i < array.length; i++) {
			root = insert(root, array[i]);
		}
		return root;
	}


	private Node insert(Node root, int x) {
		if (root == null) {
			root = new Node(x);
			return root;
		}
		
		if (x <= root.val) {
			root.left = insert(root.left, x);
			root.leftSize++;
		} else {
			root.right = insert(root.right, x);
		}
		return root;
	}


	//time O(N)
	//space O(1)
	public int getRank1(int[] array, int x)  {
		int rst = 0;
		int countX = 0;
		for (int i = 0 ; i < array.length; i++) {
			if (array[i] < x) {
				rst++;
			}
			if (array[i] == x) {
				countX ++;
			}
		}
		return countX == 0 ? rst : rst + countX - 1;
	}
	
	public static void main(String[] args) {
		Geeks_Rank_Of_An_Element_In_A_Stream re = new Geeks_Rank_Of_An_Element_In_A_Stream();
		System.out.println(re.getRank(new int[]{10,20,15,3,4,4,1}, 4));
		
		System.out.println(re.getRank(new int[]{5, 1, 14, 4, 15, 9, 7, 20, 11}, 20));
	}
}

