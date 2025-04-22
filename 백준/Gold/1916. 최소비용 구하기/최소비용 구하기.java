import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1000*100_001+1;
	static int N, M, start, end;
	static class Edge implements Comparable<Edge>{
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
	static int[][] board;
	static int[] dp;
	static ArrayList<Integer>[] graph;
	static PriorityQueue<Edge> q;
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		board = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(board[i], INF);
			board[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			board[s][e] = Math.min(board[s][e], w);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		q = new PriorityQueue<>();
		dp = new int[N+1];
		Arrays.fill(dp, INF);
		
		/* 로직 */
		q.offer(new Edge(start, 0));
		while(!q.isEmpty()) {
			Edge cur = q.poll();
			for (int next : graph[cur.v]) {
				if(dp[next] > cur.w + board[cur.v][next]) {
					dp[next] = cur.w + board[cur.v][next];
					q.offer(new Edge(next, dp[next]));
				}
			}
		}
		
		/* 출력 */
		System.out.print(dp[end]);
	}

}
