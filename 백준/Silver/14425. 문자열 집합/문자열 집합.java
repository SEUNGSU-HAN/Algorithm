import java.io.*;
import java.util.*;

public class Main {
	static class TrieNode { //트라이 노드 클래스
		TrieNode[] children;
		boolean isEndOfWord;
		
		public TrieNode() {
			children = new TrieNode[26]; //알파벳 이라서 26개 필요
			isEndOfWord = false; //처음 단어의 끝은 false
		}
	}
	
	static class Trie { //실제 트라이 클래스
		TrieNode root; //시조의 문자 루트노드
		
		public Trie() {
			root = new TrieNode();
		}
		
		//단업 삽입
		public void insert(String word) {
			TrieNode cur = root; //시작은 루트 노드부터 시작!
			
			/*
			 단어의 길이만큼 반복 돔
			 비교할 단어의 길이를 하나씩 트라이 노드에 비교하면서 트리를 만들어감
			 */
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i)-'a'; //알파벳을 index로 변환
				if(cur.children[index] == null) {
					//해당 알파벳으로 시작하는 단어가 없었다면 추가
					//있다면 어차피 다음 노드로 넘어가면 됨
					cur.children[index] = new TrieNode();
				}
				
				//다음 자식 노드로 인동
				cur = cur.children[index];
			}
			
			//단어의 끝까지 왔다면 해당 노드가 특정 단어의 끝임을 표시
			cur.isEndOfWord = true;
		}
		
		public boolean search(String str) {
			TrieNode cur = root; //시작은 루트 노드부터 시작!
			
			for (int i = 0; i < str.length(); i++) {
				int index = str.charAt(i)-'a'; //insert와 유사하게 단어의 알파벳 하나씩 index화
				
				//단어의 끝까지 가기전에 null이 나오면 해당 단어는 trie 안에 없는 단어임
				if(cur.children[index] == null) return false;
				
				//단어의 다음 알파벳 확인 (다음 자식 노드로 넘어가기)
				cur = cur.children[index];
			}
			
			//여기까지 오면 해당 단어가 들어있다는 거임
			//단! 해당 단어가 끝 단어가 아니라 특정 단어의 중간에 위치한 단어일 수 있음
			//(예제 중 beakjoononlinejudge , beakjoon)
			//그래서 cur의 끝단어 여부를 반환해줌
			return cur.isEndOfWord; 
		}
	}
	static int N, M, count;
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
		for (int i = 0; i < M; i++) {
			count += trie.search(br.readLine().trim()) ? 1 : 0;
		}
		
		/* 출력 */
		System.out.print(count);
	}
	
}
