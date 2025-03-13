import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static boolean[] visited;
	static int[] p; //각 노드들의 부모 기록
	static Queue<Integer> q;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		visited = new boolean[100_001];
		p = new int[100_001];
		
		q = new ArrayDeque<>();
		
		/* 로직 */
		//1. 재귀+dp로 풀려니 stackoverflow나고 터짐
		// 생각해보면 n, k 크기때문에 무조건 터질 것 같긴 했음
		//2. dp는 맞지만 반복문+큐를 써보자
		// -> 시간 초과 발생
		// 무지성 큐에 넣고 뺴기만 하니까 depth 가 너무 깊어지는 것 같음
		//3. dp 버리고 bfs 스타일로 해보자 (어차피 최단으로 가는 길을 찾는것과 같으니?)
		q.offer(N);
		visited[N] = true;
		p[N] = N;
		int count = -1;
		loop:
		while(!q.isEmpty()) {
			int n = q.size();
			count++;
			while(n-- > 0) {
				int v = q.poll();
				if(v == K) {
					break loop;
				}
				
				if(v > K) {
				    if (!visited[v-1] && v-1 >= 0) {
				    	p[v-1] = v;
				        visited[v-1] = true;
				        q.offer(v-1);
				    }
				}else {
					int[] next = {v-1, v+1, v*2};
					for (int nv : next) {
					    if (nv >= 0 && nv <= 100000 && !visited[nv]) {
					    	p[nv] = v;
					    	visited[nv] = true;
					        q.offer(nv);
					    }
					}
				}
			}
		}
		ArrayList<Integer> seq = new ArrayList<>();
		int idx = K;
		while(true) {
			seq.add(idx);
			if(idx == N) break;
			idx = p[idx];
		}
		Collections.reverse(seq);
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		sb.append(count).append("\n");
		for (int n : seq) {
			sb.append(n).append(" ");
		}
		System.out.println(sb);
	}
}
