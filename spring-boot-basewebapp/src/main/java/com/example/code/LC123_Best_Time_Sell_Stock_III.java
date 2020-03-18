package com.example.code;

public class LC123_Best_Time_Sell_Stock_III {
	public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices == null || prices.length == 0) {
            return maxProfit;
        }
        
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i <prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }
        
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }
        
        for (int i = 0; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, left[i] + right[i]);
        }
        return maxProfit;
    }
	
	public static void main(String[] args) {
		LC123_Best_Time_Sell_Stock_III bst = new LC123_Best_Time_Sell_Stock_III();
		int[] prices = {1,4,1,3};
		System.out.println(bst.maxProfit(prices));
	}
}
