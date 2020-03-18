package com.example.learning;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Currency_Rates_DFS {
	
    Map<String, Map<String, Double>> graph = new HashMap<>();
    
    public double[] calcEquation(List<List<String>> rates, List<List<String>> queries) {
    	buildGraph(rates);
    	String start;
    	String end;
    	double[] res = new double[queries.size()];
    	for (int i = 0; i < queries.size(); i++) {
    		start = queries.get(i).get(0);
    		end = queries.get(i).get(1);
    		double pathWeight = getPathWeight(start, end, new HashSet<>());
    		DecimalFormat df = new DecimalFormat(".##");
    		res[i] = Double.parseDouble(df.format(pathWeight));
    	}
    	return res;
    }
    
    private double getPathWeight(String start, String end, Set<String> visited) {
		if (!graph.containsKey(start) || !graph.containsKey(end)) {
			return -1.0;
		}
		
		if(graph.get(start).containsKey(end)) {
			return graph.get(start).get(end);
		}
		
		visited.add(start);
		for (Map.Entry<String, Double> neighbour : graph.get(start).entrySet()) {
			if (!visited.contains(neighbour.getKey())) {
				double productWeight = getPathWeight(neighbour.getKey(), end, visited);
				if (productWeight != -1) {
					return productWeight * neighbour.getValue();
				}
			}
		}
		return -1.0;
	}

	private void buildGraph(List<List<String>> rates) {
    	String start;
    	String end;
    	Double val;
    	for (int i = 0; i < rates.size(); i++) {
    		start = rates.get(i).get(0);
    		end = rates.get(i).get(1);
    		val = Double.parseDouble(rates.get(i).get(2));
    		graph.putIfAbsent(start, new HashMap<>());
    		graph.get(start).put(end, val);
    		graph.putIfAbsent(end, new HashMap<>());
    		graph.get(end).put(start, 1/val);
    	}
	}

	public static void main(String[] args) {
        Currency_Rates_DFS cr = new Currency_Rates_DFS();
    	List<List<String>> rates = new ArrayList<>();
    	rates.add(Arrays.asList("USD", "JPY", "110"));
    	rates.add(Arrays.asList("USD", "AUD", "1.45"));
    	rates.add(Arrays.asList("JPY", "GBP", "0.0070"));
    	List<List<String>> queries = new ArrayList<>();
    	queries.add(Arrays.asList("GBP", "AUD"));
    	double[] answer = cr.calcEquation(rates, queries);
//    	DecimalFormat df = new DecimalFormat(".##");
    	System.out.print(answer);

    }
}

