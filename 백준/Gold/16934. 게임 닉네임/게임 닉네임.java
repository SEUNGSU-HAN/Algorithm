import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static class TrieNode {
		Map<Character, TrieNode> children;
		int isEndOfWord;
		
		public TrieNode() {
			children = new TreeMap<>();
			isEndOfWord = 0;
		}
	}
	static class Trie {
		TrieNode root;
		
		public Trie() {
			root = new TrieNode();
		}
		
		public void insert(String word) {
			TrieNode cur = root;
			
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				cur.children.putIfAbsent(c, new TrieNode());
				cur = cur.children.get(c);
			}
			
			cur.isEndOfWord++;
		}
		
		public int[] search(String word) {
			TrieNode cur = root;
			
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				
				if(cur.children.get(c) == null) return new int[] {i+1, 1};
				
				cur = cur.children.get(c);
			}
			
			return new int[] {word.length(), cur.isEndOfWord+1};
		}
	}
	static Trie nick;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		nick = new Trie();
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			StringBuilder insb = new StringBuilder();
			String w = br.readLine().trim();
			insb.append(w);
			int[] idxAndRepeat = nick.search(w);
			nick.insert(w);
			if(idxAndRepeat[1] > 1) {
				insb.append(idxAndRepeat[1]);
				sb.append(insb.substring(0, insb.length()));
			}
			else sb.append(insb.substring(0, idxAndRepeat[0]));
			sb.append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
