package two.sigma.oa;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author franksun
 * 
 * Feb 20, 2020
 * 
 */
public class LC_918_Maximum_Sum_Circular_Subarray {
	//Prefix Sums + Monoqueue
	 public int maxSubarraySumCircular1(int[] A) {
		 int N = A.length;
		 //compute P[j] = B[0] + B[1] + ... + B[j - 1]
		 //for fixed array B = A + A
		 int[] p = new int[2 * N + 1];
		 for (int i = 0; i < 2 * N; i++) {
			 p[i + 1] = p[i] + A[i % N];
		 }
		 
		// Want largest P[j] - P[i] with 1 <= j - i <= N (candidate with max sum with length N)
	    // For each j, want smallest P[i] with i >= j - N
		 
		 int ans = A[0];
		
		 // deque: i's, increasing by P[i]
	    Deque<Integer> deque = new ArrayDeque<>();
	    deque.offer(0);
	    
	    for (int j = 1; j <= 2 * N; j++) {
	    	//if smallest i is too small, remove it
	    	//越界了，不可能出现现在这个i开始的长度为N的candidate
	    	if (deque.peekFirst() < j - N) {
	    		deque.pollFirst();
	    	}
	    	// The optimal i is deque[0], for cand. answer P[j] - P[i].
	    	ans = Math.max(ans, p[j] - p[deque.peekFirst()]);
	    	
	    	//remove any i1's with p[i2] <= p[i1]
	    	while (!deque.isEmpty() && p[j] <= p[deque.peekLast()]) {
                deque.pollLast();
	    	}
            deque.offerLast(j);
        }
        return ans;
	 }
	 
	 /*
	    There are two cases:
	    1. not wrapped around, let max be max1;
	    2. wrapped around, let max be max2;
	    Final result should be Math.max(max1, max2);
	    Computing max1 is easy
	    To compute max2, instead find the min sum in range [1, A.length - 2], then max2 = total - min;
	    */
	    public int maxSubarraySumCircular(int[] A) {
	    	int max = Integer.MIN_VALUE;
	    	int sum = 0;
	    	int total = 0;
	    	for (int i = 0; i < A.length; i++) {
	    		sum += A[i];
	    		total += A[i];
	    		max = Math.max(max, sum);
	    		sum = sum > 0 ? sum : 0;
	    	}
	    	
	    	if (A.length <= 2) {
	    		return max;
	    	}
	    	
	    	sum = 0;
	    	int min = Integer.MAX_VALUE;
	    	for (int i = 1; i < A.length - 1; i++) {
	    		sum += A[i];
	    		min = Math.min(min, sum);
	    		sum = sum < 0 ? sum : 0;
	    	}
	    	return Math.max(max, total - min);
	    }
	    
	    public static void main(String[] args) {
	    	LC_918_Maximum_Sum_Circular_Subarray ms = new LC_918_Maximum_Sum_Circular_Subarray();
	    	System.out.println(ms.maxSubarraySumCircular(new int[] {5,-3,5}));
	    	System.out.println(ms.maxSubarraySumCircular1(new int[] {5,-3,5}));
		}
}
