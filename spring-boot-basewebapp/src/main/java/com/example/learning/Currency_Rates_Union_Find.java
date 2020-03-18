package com.example.learning;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Rates: ['USD', 'JPY', 110] ['USD', 'AUD', 1.45] ['JPY', 'GBP', 0.0070]
//From/To currency ['GBP', 'AUD']
//1 GBP = 1.88 AUD
public class Currency_Rates_Union_Find {

	Map<String, String> parents = new HashMap<>();
	Map<String, Double> vals = new HashMap<>();

	public double calcEquation(List<List<String>> rates, List<String> queries) {

		for (int i = 0; i < rates.size(); i++) {
			union(rates.get(i).get(0), rates.get(i).get(1), Double.parseDouble(rates.get(i).get(2)));
		}

		String x = queries.get(0);
		String y = queries.get(1);
		return (parents.containsKey(x) && parents.containsKey(y) && (x == y || find(x) == find(y)))
				? vals.get(x) / vals.get(y)
				: -1.0;

	}

	private void union(String x, String y, double val) {
		add(x);
		add(y);
		String px = find(x);
		String py = find(y);
		parents.put(px, py);
		vals.put(px, val * vals.get(y) / vals.get(x));
	}

	private void add(String x) {
		if (parents.containsKey(x)) {
			return;
		}
		parents.put(x, x);
		vals.put(x, 1.0);
	}

	private String find(String x) {
		String parent = parents.getOrDefault(x, x);
		if (x != parent) {
			String ppx = find(parent);
			parents.put(x, ppx);
			vals.put(x, vals.get(x) * vals.get(parent));
		}
		return parents.getOrDefault(x, x);
	}

	public static void main(String[] args) {
		Currency_Rates_Union_Find cr = new Currency_Rates_Union_Find();
		List<List<String>> rates = new ArrayList<>();
		rates.add(Arrays.asList("USD", "JPY", "110"));
		rates.add(Arrays.asList("USD", "AUD", "1.45"));
		rates.add(Arrays.asList("JPY", "GBP", "0.0070"));
		List<String> queries = Arrays.asList("GBP", "AUD");
		double answer = cr.calcEquation(rates, queries);
		DecimalFormat df = new DecimalFormat(".##");
		System.out.print(df.format(answer));
	}
}
