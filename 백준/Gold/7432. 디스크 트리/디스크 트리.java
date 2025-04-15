import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static class TrieNode {
		Map<String, TrieNode> childern;
		
		public TrieNode() {
			childern = new TreeMap<>();
		}
	}
	
	static class Trie {
		TrieNode root;
		
		public Trie() {
			root = new TrieNode();
		}
		
		public void insert(String path) {
			String[] dirs = path.split("\\\\");
			TrieNode cur = root;
			
			for (String dir : dirs) {
				cur.childern.putIfAbsent(dir,  new TrieNode());
				cur = cur.childern.get(dir);
			}
		}
		
		public String print() {
			StringBuilder sb = new StringBuilder();
			printRecur(root, 0, sb);
			return sb.toString();
		}

		private void printRecur(TrieNode cur, int depth, StringBuilder sb) {
			for (Map.Entry<String, TrieNode> entry : cur.childern.entrySet()) {
				for (int i = 0; i < depth; i++) {
					sb.append(" ");
				}
				
				sb.append(entry.getKey()).append("\n");
				
				printRecur(entry.getValue(), depth+1, sb);
			}
		}
	}
	static Trie trie;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		trie = new Trie();
		
		/* 로직 */
		for (int i = 0; i < N; i++) {
			trie.insert(br.readLine().trim());
		}
		
		
		/* 출력 */
		System.out.println(trie.print());
		
	}
	

}
