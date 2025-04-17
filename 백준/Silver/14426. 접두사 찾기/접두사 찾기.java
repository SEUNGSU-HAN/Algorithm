import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static class TrieNode {
		TrieNode[] childern;
		boolean isEndOfWord;
		
		public TrieNode() {
			childern = new TrieNode[26];
			isEndOfWord = false;
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
				int idx = word.charAt(i)-'a';
				
				if(cur.childern[idx] == null) {
					cur.childern[idx] = new TrieNode();
				}
				
				cur = cur.childern[idx];
			}
			
			cur.isEndOfWord = true;
		}
		
		public boolean isPrefix(String word) {
			TrieNode cur = root;
			
			for (int i = 0; i < word.length(); i++) {
				int idx = word.charAt(i)-'a';
				
				if(cur.childern[idx] == null) return false;
				
				cur = cur.childern[idx];
			}
			
			return true;
		}
	}
	static Trie trie;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		trie = new Trie();
		for (int i = 0; i < N; i++) {
			trie.insert(br.readLine().trim());
		}
		
		/* 로직 */
		int ans = 0;
		for (int i = 0; i < M; i++) {
			if(trie.isPrefix(br.readLine().trim())) ans++;
		}

		/* 출력 */
		System.out.print(ans);
	}

}
