package com.example.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC_128_Longest_Consecutive_Sequence {
	public int longestConsecutive(int[] nums) {
		int rst = 0;
		if (nums == null || nums.length == 0) {
			return rst;
		}
		Set<Integer> set = new HashSet<>();
		
		for (int num : nums) {
			set.add(num);
		}
		
		for (int num : nums) {
			if (!set.contains(num - 1)) {
				int curLen = 1;
				int curNum = num;
				
				while (set.contains(curNum + 1)) {
					curLen++;
					curNum++;
				}
				rst = Math.max(rst, curLen);
			}
		}
		
		return rst;
	}
	
	public int longestConsecutive1(int[] nums) {
		int longestConsecutiveSeqLen = 0;
		if (nums == null || nums.length == 0) {
			return longestConsecutiveSeqLen;
		}
		longestConsecutiveSeqLen = 1;
		// to track index of each element in array
        Map<Integer, Integer> mp = new HashMap<>();
		
		int n = nums.length;
		Union union = new Union(n);
		
		
		for (int i = 0; i < n; i++) {
			int e = nums[i];
            if(mp.containsKey(e)){
                continue;
            }
            mp.put(e, i);
            if(mp.containsKey(e-1)){
                longestConsecutiveSeqLen = Math.max(longestConsecutiveSeqLen, union.union(i, mp.get(e-1)));
            }
            
            if(mp.containsKey(e+1)){
                longestConsecutiveSeqLen = Math.max(longestConsecutiveSeqLen, union.union(i, mp.get(e+1)));
            }
            
        }
        return longestConsecutiveSeqLen;
	}
		
	class Union {
		int[] parents;
		int[] ranks;
		int[] sizes;
		
		Union(int n) {
			parents = new int[n];
	        ranks = new int[n];
	        sizes = new int[n];
	        for(int i = 0; i < n; i++){
	            parents[i] = i;
	        }
	        Arrays.fill(sizes, 1);
		}
		
		public int union(int a, int b) {
			// union by rank heuristic
	        int rootA = find(a);
	        int rootB = find(b);
			if(rootA == rootB) {
			    return -1;
			}
			
			if (ranks[rootA] > ranks[rootB]) {
				parents[rootB] = rootA;
				sizes[rootA] += sizes[rootB];
				return sizes[rootA];
			} else {
				parents[rootA] = rootB;
				sizes[rootB] += sizes[rootA];
				if (ranks[rootA] == ranks[rootB]) {
					ranks[rootB]++;
				}
				return sizes[rootB];
			}
		}
		
		public int find(int i) {
			if (parents[i] != i) {
				parents[i] = find(parents[i]);
			}
			return parents[i];
		}
	}
	
	public static void main(String[] args) {
		LC_128_Longest_Consecutive_Sequence lcs = new LC_128_Longest_Consecutive_Sequence();
		int[] nums = {100, 4, 200, 3, 1, 2};
		System.out.print(lcs.longestConsecutive1(nums));
		
	}
}
