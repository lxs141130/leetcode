package com.example.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_399_Evaluate_Division_Union_Find {
	Map<String, String> parents = new HashMap<>();
	Map<String, Double> vals = new HashMap<>();

	public double[] calcEquation(List<List<String>> equs, double[] values, List<List<String>> queries) {
		double[] res = new double[queries.size()];
		for (int i = 0; i < values.length; ++i) {
			union(equs.get(i).get(0), equs.get(i).get(1), values[i]);
		}
		for (int i = 0; i < queries.size(); ++i) {
			String x = queries.get(i).get(0);
			String y = queries.get(i).get(1);
			if (parents.containsKey(x) && parents.containsKey(y)) {
				if (x.equals(y)) {
					res[i] = 1.0;
				} else if (find(x) == find(y)){
					res[i] = vals.get(x) / vals.get(y);
				} else {
					res[i] = -1.0;
				}
			} else {
				res[i] = -1.0;
			}
//			res[i] = (parents.containsKey(x) && parents.containsKey(y) && find(x) == find(y))
//					? vals.get(x) / vals.get(y)
//					: -1.0;
		}
		return res;
	}

	public void add(String x) {
		if (parents.containsKey(x)) {
			return;
		}
		parents.put(x, x);
		vals.put(x, 1.0);
	}

	public String find(String x) {
		String p = parents.getOrDefault(x, x);
		if (x != p) {
			String pp = find(p);
			vals.put(x, vals.get(x) * vals.get(p));
			parents.put(x, pp);
		}
		return parents.getOrDefault(x, x);
	}

	public void union(String x, String y, double v) {
		add(x);
		add(y);
		String px = find(x), py = find(y);
		parents.put(px, py);
		vals.put(px, v * vals.get(y) / vals.get(x));
	}
	
	public static void main(String[] args) {
		LC_399_Evaluate_Division_Union_Find ed = new LC_399_Evaluate_Division_Union_Find();
		List<List<String>> equs = new ArrayList<>();
		equs.add(Arrays.asList("a","b"));
		equs.add(Arrays.asList("b","c"));
		double[] values = {2.0,3.0};
		List<List<String>> queries = new ArrayList<>();
		queries.add(Arrays.asList("a","c"));
		queries.add(Arrays.asList("b","a"));
		queries.add(Arrays.asList("a","e"));
		queries.add(Arrays.asList("a","a"));
		queries.add(Arrays.asList("x","x"));
		System.out.println(ed.calcEquation(equs, values, queries));
	}
}