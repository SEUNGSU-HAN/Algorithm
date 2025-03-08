import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] count;
	static ArrayList<Integer>[] graph;
	static PriorityQueue<Integer> q;

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
		//집입차수0인 애들 먼저 넣되 난이도 낮은 애들먼저 처리
		// => 처리 가능한 애들중 난이도가 쉬운 애들부터 처리
		q = new PriorityQueue<>(Integer::compare);
		
		/* 로직 */
		//진입차수 0인 애들 큐에 추가
		for (int i = 1; i <= N; i++) {
			if(count[i] == 0) q.offer(i);
		}
		
		//위상 정렬 스타트
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
