import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 999999999;
	static int V, E, K;
	static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	static ArrayList<Edge>[] graph;
	static PriorityQueue<Edge> queue;
	static int[] dp;
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		graph = new ArrayList[V+1];
		for (int i = 0; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		dp = new int[V+1];
		Arrays.fill(dp, INF);
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[s].add(new Edge(e, w));
		}
		queue = new PriorityQueue<>();
		visited = new boolean[V+1];
		
		/* 로직 */
		//프림 사용 하려했지만 는 안풀림
		//다익스트라 사용해야함!!
		
		//시작 정점 정해서 스타트
		queue.offer(new Edge(K, 0));
		dp[K] = 0; //시작점 초기화
		
		while(!queue.isEmpty()) {			
			Edge cur = queue.poll();
			int v = cur.v;
			int w = cur.w;
			if(visited[v]) continue;
			visited[v] = true;
			for (Edge next : graph[v]) {
				if(dp[next.v] > w + next.w) {
					//갱신함 (더 빠른 길로)
					dp[next.v] = w + next.w;
					queue.offer(new Edge(next.v, dp[next.v]));
				}
			}
		}
		
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if(dp[i] == INF) sb.append("INF");
			else sb.append(dp[i]);
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
