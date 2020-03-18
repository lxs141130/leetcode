package com.example.code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LC_269_Alien_Dictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        
        for (String s : words) {
            for (char c : s.toCharArray()) {
                indegree.put(c, 0);
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            String s1 = words[i];
            String s2 = words[i + 1];
            
            int minLen = s1.length() < s2.length() ? s1.length() : s2.length();
            
            for (int j = 0; j < minLen; j++) {
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = map.getOrDefault(c1, new HashSet<>());
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }                    
                    break;
                }
                
            }
        }
        
        Queue<Character> q = new LinkedList<>();
        for (Map.Entry<Character,Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        }
        
        String rst = "";
        
        while (!q.isEmpty()) {
            char c = q.poll();
            rst += c;
            
            if (map.containsKey(c)) {
                for (char ch : map.get(c)) {
                    indegree.put(ch, indegree.get(ch) - 1);
                    if (indegree.get(ch) == 0) {
                        q.add(ch);
                    }
                }
            }
            
        }
        
        return rst.length() == indegree.size() ? rst : "";
    }
    
    public String alienOrder1(String[] words) { 
    	Map<Character, Set<Character>> graph = new HashMap<>();
    	String ans = null;
    	Set<Character> charSet = new HashSet<>(); //Maintain a set of chars that are not in the graph.
    	buildGraph(words, graph, charSet);
    	ans = topoSortDFS(graph, charSet);
    	return ans;
    }
    
    private String topoSortDFS(Map<Character, Set<Character>> graph, Set<Character> charSet) {
		// TODO Auto-generated method stub
    	StringBuffer ans = new StringBuffer();
    	boolean[] visited = new boolean[26]; //permanent marker.
    	boolean[] tempMark = new boolean[26]; //temporary marker
    	
    	for (Character v : graph.keySet()) {
    		if (!visited[v - 'a']) {
    			if (!visitDFS(ans, graph, visited, v, tempMark)) {
    				return "";
    			}				
    		}	
    	}
    	
    	for (Character c : charSet) { //insert orphan chars.
    		ans.insert(0, c);
    	}
    	return ans.reverse().toString();
	}

	private boolean visitDFS(StringBuffer ans, Map<Character, Set<Character>> graph, boolean[] visited, Character node,
			boolean[] marked) {
		if (marked[node - 'a']) {  //if temporally marked already, it is not DAG. return failure(false);
			return false;
		} 
		if (!visited[node - 'a']) {
			marked[node - 'a'] = true; //mark the current node temporally.
			Set<Character> set = graph.get(node);
			if (set != null) {
				for (Character m : graph.get(node)) {
					if (!visitDFS(ans, graph, visited, m, marked))
						return false;
				}
			}
			//there is no adjacent node, it is a leaf, output the node(char).
			visited[node - 'a'] = true; //mark the node permanently.
			marked[node - 'a'] = false; //remove the temp mark.
			ans.append(node);
		}
		return true;
	}

	private void buildGraph(String[] words, Map<Character, Set<Character>> graph, Set<Character> charSet) {
		getCharSet(words, charSet); //Find all chars in the words.
		for (int i = 0; i < words.length - 1; i++) {
			int j = i + 1;
			for (int k = 0; k < Math.min(words[i].length(), words[j].length()); k++) {
				if (words[i].charAt(k) != words[j].charAt(k)) {
					charSet.remove(words[i].charAt(k));
					charSet.remove(words[j].charAt(k));
					Set<Character> adjList = graph.getOrDefault(words[i].charAt(k), new HashSet<>());
					adjList.add(words[j].charAt(k));
					graph.put(words[i].charAt(k), adjList);
					break;
				}
			}
		}
	}
	
	private Set<Character> getCharSet(String[] words, Set<Character> charSet) {
		for (String word : words) {
			for (char c : word.toCharArray()) {
				charSet.add(c);
			}
		}
		return charSet;
	}

	public static void main(String[] args) {
    	LC_269_Alien_Dictionary ad = new LC_269_Alien_Dictionary();
    	String[] words = {"wrt",
    			  "wrf",
    			  "er",
    			  "ett",
    			  "rftt"};
    	System.out.print(ad.alienOrder1(words));
	}
}
