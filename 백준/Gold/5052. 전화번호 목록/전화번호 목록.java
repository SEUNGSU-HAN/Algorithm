import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static class TrieNode {
		TrieNode[] children;
		boolean isEndOfNumber;
		
		public TrieNode() {
			children = new TrieNode[10]; //숫자 0~9
			isEndOfNumber = false;
		}
	}
	static class Trie {
		TrieNode root;
		
		public Trie() {
			root = new TrieNode();
		}
		
		public void insert(String number) {
			TrieNode cur = root;
			for (int i = 0; i < number.length(); i++) {
				int index = number.charAt(i)-'0';
				
				
				if(cur.children[index] == null) {
					cur.children[index] = new TrieNode();
				}
				
				cur = cur.children[index];
			}
			
			cur.isEndOfNumber = true;
		}
		
		public boolean search(String number) {
			TrieNode cur = root;
			
			for (int i = 0; i < number.length(); i++) {
				int index = number.charAt(i)-'0';
				
				if(cur.children[index] == null) {
					//트라이 안에 있는 숫자열 중 하나가 해당 순자열의 접두어임
					//(끝단어 임에 따라 알 수 있음)
					if(cur.isEndOfNumber) return true;
					else return false;
				}
				
				cur = cur.children[index];
			}
			//내가 넣으려는 숫자가 이미 트라이 안에 존재 함
			return true;
		}
	}
	static Trie trie;
	static String[] numbers;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 0; test_case < T; test_case++) {
			/* 입력 */
			N = Integer.parseInt(br.readLine());
			
			/* 초기화 */
			trie = new Trie();
			numbers = new String[N];
			for (int i = 0; i < N; i++) {
				numbers[i] = br.readLine().trim();
			}
			
			/* 로직 */
			boolean isConsistency = true;
			for (String number : numbers) {
				if(!trie.search(number)) trie.insert(number);
				else {
					isConsistency = false;
					break;
				}
			}
			
			sb.append(isConsistency ? "YES\n" : "NO\n");	
		}
		/* 출력 */
		System.out.print(sb);
	}


}
