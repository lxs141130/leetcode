package microsoft.Onsite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC127_Word_Ladder {
	public int ladderLength(String start, String end, Set<String> dict) {
		// write your code here
		if (start.length() != end.length()) {
			return 0;
		}
		int step = 0;
		Queue<String> q = new LinkedList<>();
		q.add(start);
		int curSize = 1;
		int nextSize = 0;
		while (!q.isEmpty()) {
			String s = q.poll();
			curSize--;
			if (s.equals(end)) {
				return step + 1;
			}
			char[] charArray = s.toCharArray();
			for (int i = 0; i < s.length(); i++) {
				char c = charArray[i];
				for (char j = 'a'; j <= 'z'; j++) {
					if (j != c) {
						charArray[i] = j;
						String newString = new String(charArray);
						if (newString.equals(end)) {
							return step + 1;
						}
						if (dict.contains(newString)) {
							q.add(newString);
							nextSize++;
							dict.remove(newString);
						}
					}
				}
				charArray[i] = c;
			}
			if (curSize == 0) {
				step++;
				curSize = nextSize;
				nextSize = 0;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		LC127_Word_Ladder wl = new LC127_Word_Ladder();
		String beginWord = "hit";
		String endWord = "cog";
		Set<String> wordList = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
		System.out.println(wl.ladderLength(beginWord, endWord, wordList));
	}
}
