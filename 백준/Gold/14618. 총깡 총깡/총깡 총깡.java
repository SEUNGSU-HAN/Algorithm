import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 987654321;
	static int N, M, J, K;
	static class Edge implements Comparable<Edge>{
		int v, w;
		
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	static char[] homeList;
	static ArrayList<Edge>[] graph;
	static int[] dist;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		J = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		homeList = new char[N+1];
		Arrays.fill(homeList, 'X');
		homeList[J] = 'J';
		
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int[] aHome = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int a = Integer.parseInt(st.nextToken());
			homeList[a] = 'A';
			aHome[i] = a;
		}
		
		int[] bHome = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int b = Integer.parseInt(st.nextToken());
			homeList[b] = 'B';
			bHome[i] = b;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[v].add(new Edge(w, c));
			graph[w].add(new Edge(v, c));
			
		}
		
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		/* 로직 */
		//다익스트라 알고리즘을 사용해보자
		dijkstra(J); //진서를 기준으로 시작
		
		int minDist = INF;
		char homeType = ' ';
		
		//A형 집을 먼저 검사
		for(int a : aHome) {
			if(minDist > dist[a]) {
				minDist = dist[a];
				homeType = homeList[a];
			}
		}
		
		//B형 집을 검사
		for(int b : bHome) {
			if(minDist > dist[b]) {
				minDist = dist[b];
				homeType = homeList[b];
			}
		}
		
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		if(minDist == INF) sb.append(-1);
		else sb.append(homeType).append("\n").append(minDist);
		System.out.print(sb.toString());
	}

	static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
						
			for(Edge e : graph[cur.v]) {
				if(dist[e.v] > dist[cur.v] + e.w) {
					dist[e.v] = dist[cur.v] + e.w;
					pq.offer(e);
				}
			}
		}
	}

}
