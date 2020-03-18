package microsoft.Onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franksun
 * 
 * Feb 25, 2020
 * 
 */
public class LC_212_Word_Search_II {
	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		if (board.length == 0 || words.length == 0) {
			return res;
		}
		TrieNode root = buildTrie(words);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dfs(res, board, i, j, root);
			}
		}
		return res;
	}
	
	private void dfs(List<String> res, char[][] board, int i, int j, TrieNode node) {
		char c = board[i][j];
		//failure case
		if (c == '#' || node.children[c - 'a'] == null) {
			return;
		}
		
		node = node.children[c- 'a'];
		//this is a leaf node
		if (node.word != null) {
			res.add(node.word);
			node.word = null;// remove duplicate
		}
		
		board[i][j] = '#';
		if (i > 0) {
			dfs(res, board, i - 1, j, node);
		}
		if (j > 0) {
			dfs(res, board, i, j - 1, node);
		}
		if (i < board.length) {
			dfs(res, board, i + 1, j, node);
		}
		if (j < board[0].length) {
			dfs(res, board, i, j + 1, node);
		}
		
		board[i][j] = c;
	}

	public TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode p = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (p.children[c - 'a'] == null) {
					p.children[c - 'a'] = new TrieNode();
				}
				p = p.children[c - 'a'];
			}
			p.word = word;
		}
		return root;
	}
	
	class TrieNode {
		TrieNode[] children = new TrieNode[26];
		String word;
	}
}
