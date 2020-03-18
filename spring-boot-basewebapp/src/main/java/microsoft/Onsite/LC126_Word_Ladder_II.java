package microsoft.Onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LC126_Word_Ladder_II {

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>(wordList);
		Set<String> curWordSet = new HashSet<>();
		
		//current level word set
		curWordSet.add(beginWord);
		//remove all visited words
		dict.removeAll(curWordSet);
		
		//map use to mapping the current word to its potential word list
		//hit -> [hot]
		Map<String, List<String>> map = new HashMap<>();
		
		//result list
		List<List<String>> res = new ArrayList<>();

		//generate a map, which will have the correct mapping for a word to its potentials
		//remember to remove already added words
		
		//hit ->[hot]->[dot, -> [dog]
		//				lot] -> [log]
		
		while (!curWordSet.isEmpty()) {
			//already touch the end, no need to expand the map to store any info after end word
			if (curWordSet.contains(endWord)) {
				break;
			}
			Set<String> nextWordSet = new HashSet<>();
			for (String curWord : curWordSet) {
				List<String> potentials = generatePotentials(curWord, dict);
				if (!potentials.isEmpty()) {
					map.put(curWord, potentials);
					nextWordSet.addAll(potentials);
				}
			}
			dict.removeAll(nextWordSet);
			curWordSet = nextWordSet;
		}

		List<String> temp = new ArrayList<String>();
		//add as there is no entry for begin word inside map
		temp.add(beginWord);
		dfs(res, map, temp, beginWord, endWord);
		return res;

	}

	private void dfs(List<List<String>> res, Map<String, List<String>> map, List<String> temp, String beginWord,
			String endWord) {
		if (beginWord.equals(endWord)) {
			res.add(new ArrayList<>(temp));
			return;
		}

		if (map.containsKey(beginWord)) {
			for (String word : map.get(beginWord)) {
				temp.add(word);
				dfs(res, map, temp, word, endWord);
				temp.remove(temp.size() - 1);
			}
		}
	}

	// generate potential transformations for current word
	private List<String> generatePotentials(String curWord, Set<String> dict) {
		List<String> list = new ArrayList<>();

		char[] wordUnit = curWord.toCharArray();

		for (int i = 0; i < curWord.length(); i++) {
			char c = wordUnit[i];
			for (char j = 'a'; j <= 'z'; j++) {
				if (c != j) {
					wordUnit[i] = j;
					String newWord = new String(wordUnit);
					if (dict.contains(newWord)) {
						list.add(newWord);
					}
				}
			}
			wordUnit[i] = c;
		}
		return list;
	}

	public static void main(String[] args) {
		LC126_Word_Ladder_II wl = new LC126_Word_Ladder_II();
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
		System.out.println(wl.findLadders(beginWord, endWord, wordList));
	}
}

//package com.example.code;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class LC126_Word_Ladder_II {
//	public List<String> generatePotentials(String beginWord, Set<String> wordList) {
//        List<String> list = new ArrayList<>();
//        char[] chars = beginWord.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            char o = chars[i];
//            for (char c = 'a'; c <= 'z'; c++) {
//                if (c == o) {
//                    continue;
//                }
//                chars[i] = c;
//                String newWord = new String(chars);
//                if (wordList.contains(newWord)) {
//                    list.add(newWord);
//                }
//            }
//            chars[i] = o;
//        }
//        return list;
//    }
//
//    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//        Set<String> dict = new HashSet<>(wordList);
//        Set<String> start = new HashSet<>();
//        start.add(beginWord);
//        dict.removeAll(start); // the dict contains the beginWord, remove the loop
//
//        Map<String, List<String>> map = new HashMap<>();
//        List<List<String>> res = new ArrayList<>();
//
//        while(!start.isEmpty()) {
//            if (start.contains(endWord)) {
//                break;
//            }
//            Set<String> set = new HashSet<>();
//            for (String w : start) {
//                List<String> po = generatePotentials(w, dict);
//                if (!po.isEmpty()) {
//                    map.put(w, po);
//                    set.addAll(po);
//                }
//            }
//            dict.removeAll(set);
//            start = set;
//        }
//        List<String> temp = new ArrayList<>();
//        temp.add(beginWord);
//        dfs(beginWord, endWord, map, res, temp);
//        return res;
//    }
//
//    public void dfs(String beginWord, String endWord, Map<String, List<String>> map, List<List<String>> res, List<String> temp) {
//        if (beginWord.equals(endWord)) {
//            res.add(new ArrayList<>(temp));
//            return;
//        }
//        if (map.containsKey(beginWord)) {
//            for (String w : map.get(beginWord)) {
//                temp.add(w);
//                dfs(w, endWord, map, res, temp);
//                temp.remove(temp.size() - 1);
//            }
//        }
//    }
//	
//	public static void main(String[] args) {
//		LC126_Word_Ladder_II wl = new LC126_Word_Ladder_II();
//		String beginWord = "hit";
//		String endWord = "cog";
//		List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
//		System.out.println(wl.findLadders(beginWord, endWord, wordList));
//	}
//}
//
