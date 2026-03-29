import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 987654321;
	static int N, M, P, Q;
	static class Edge implements Comparable<Edge>{
		int v, w;
		
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}
	static ArrayList<Edge>[] graph;
	static int[] dist;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new Edge(v, w));
			graph[v].add(new Edge(u, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int homeCandidate = Integer.parseInt(st.nextToken());
		int conviCandidate = Integer.parseInt(st.nextToken());
		
		int[] homeList = new int[homeCandidate];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<homeCandidate; i++) {
			homeList[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] conviList = new int[conviCandidate];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<conviCandidate; i++) {
			conviList[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		//다익스트라를 써보자		
		int homeNum = 0;
		int minDist = INF;
		
		Dijkstra(conviList);

		for(int h : homeList) {
			if(minDist == dist[h]) {
				homeNum = homeNum > h ? h : homeNum;
			}else if(minDist > dist[h]) {
				homeNum = h;
				minDist = dist[h];
			}
		}
		
		/* 출력 */
		System.out.print(homeNum);
	}

	static void Dijkstra(int[] cl) {
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(int c : cl) {
			pq.offer(new Edge(c, 0));
			dist[c] = 0;
		}
				
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(cur.w > dist[cur.v]) continue;
			
			for(Edge next : graph[cur.v]) {
				if(dist[next.v] > next.w + dist[cur.v]) {
					dist[next.v] = next.w + dist[cur.v];
					pq.offer(next);
				}
			}
		}
		
	}

}
