package microsoft.Onsite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author franksun
 * 
 * Feb 26, 2020
 * 
 */
public class LC_269_Alien_Dictionary {
	 
	Map<Character, Set<Character>> graph = new HashMap<>();
	Map<Character, Integer> indegree = new HashMap<>();
	
	public String alienOrder(String[] words) {
		 buildGraph(words);
		 return topoSort();
	}

	private void buildGraph(String[] words) {
		populateCharset(words);
		for (int i = 0; i < words.length - 1; i++) {
			String word1 = words[i];
			String word2 = words[i + 1];
			int minLen = Math.min(word1.length(), word2.length());
			for (int j = 0; j < minLen; i++) {
				char c1 = word1.charAt(j);
				char c2 = word2.charAt(j);
				if (c1 != c2) {
					Set<Character> adjSet = graph.getOrDefault(c1, new HashSet<>());
					if (!adjSet.contains(c2)) {
						adjSet.add(c2);
						graph.put(c1, adjSet);
						indegree.put(c2, indegree.get(c2) + 1);
					}
					break;
				}
			}
		}
	}
	
	private void populateCharset(String[] words) {
		for (String  word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0);
            }
        }
	}

	private String topoSort() {
		StringBuilder sb = new StringBuilder();
		Queue<Character> q = new LinkedList<>();
		for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
			if (entry.getValue() == 0) {
				q.offer(entry.getKey());
			}
		}
		while (!q.isEmpty()) {
			char top = q.poll();
			sb.append(top);
			Set<Character> adjSet = graph.get(top);
			for (char c : adjSet) {
				int cnt = indegree.get(c);
				cnt--;
				indegree.put(c, cnt);
				if(cnt == 0) {
					q.offer(c);
				}
			}
		}
		return sb.length() == indegree.size() ? sb.toString() : "";
	}
}
