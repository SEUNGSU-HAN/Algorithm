import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] graph;
	static int[] count;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		count = new int[N+1];
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			count[e]++;
		}
		q = new ArrayDeque<>();
		
		/* 로직 */
		//진입차수가 0인 노드 큐에 추가
		for (int i = 1; i <= N; i++) {
			if(count[i] == 0) q.offer(i);
		}
		
		//위상 정렬
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			for (int next : graph[cur]) {
				if(--count[next] == 0) q.offer(next);
			}
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
