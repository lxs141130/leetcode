package com.example.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LC_322_Reconstruct_Itinerary {
	
	Map<String, PriorityQueue<String>> graph = new HashMap<>();
	LinkedList<String> rst = new LinkedList<>();
	
	public List<String> findItinerary(List<List<String>> tickets) {
        
        
        for (List<String> list : tickets) {
            String from = list.get(0);
            String to = list.get(1);
            
            if (!graph.containsKey(from)) {
            	PriorityQueue<String> pq = new PriorityQueue<>();
            	graph.put(from, pq);
            }
            graph.get(from).add(to);
        }
        
        dfs("JFK");
        return rst;
    }
    
    public void dfs(String s){
        PriorityQueue<String> q = graph.get(s);
        while(q != null && !q.isEmpty()){
            dfs(q.poll());
        }
        rst.addFirst(s);
    }
	
	public static void main(String[] args) {
		LC_322_Reconstruct_Itinerary ri = new LC_322_Reconstruct_Itinerary();
		List<String> list1 = Arrays.asList("JFK","SFO");
		List<String> list2 = Arrays.asList("JFK","ATL");
		List<String> list3 = Arrays.asList("SFO","ATL");
		List<String> list4 = Arrays.asList("ATL","JFK");
		List<String> list5 = Arrays.asList("ATL","SFO");
		List<List<String>> tickets = new ArrayList<>();
		tickets.add(list1);
		tickets.add(list2);
		tickets.add(list3);
		tickets.add(list4);
		tickets.add(list5);
		
		System.out.println(ri.findItinerary(tickets));
	}
	
	
//	[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//	
//			["JFK",["ATL","SFO"]],
//			["SFO",["ATL"]],
//			["ATL",["JFK","SFO"]]	
//					
				
// JFK -> ATL -> JFK -> SFO -> ATL -> SFO			
}
