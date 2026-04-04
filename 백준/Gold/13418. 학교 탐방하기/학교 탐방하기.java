import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static class Edge {
		int u, v, w;
		
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	static PriorityQueue<Edge> minRoad, maxRoad;
	static int[] parent;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		minRoad = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		maxRoad = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.w, o1.w));
		parent = new int[N+1];
		
		for(int i=0; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			//오르막이면 가중치 1, 내리막이면 0 으로 커스텀(원랜 반대임)
			int w = Integer.parseInt(st.nextToken()) == 1 ? 0 : 1;
			
			minRoad.add(new Edge(u, v, w));
			maxRoad.add(new Edge(u, v, w));
		}
				
		/* 로직 */
		
		init();
		int minK = corse(minRoad);
		init();
		int maxK = corse(maxRoad);
		
		
		/* 출력 */
		System.out.print(maxK*maxK-minK*minK);
	}
	
	static int corse(PriorityQueue<Edge> pq) {
		int k = 0;
		int count = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(union(cur.u, cur.v)) {
				count++;
				k += cur.w;
			}
			
			if(count == N) break;
		}
		
		return k;
	}
	
	static int find(int x) {
		if(parent[x] == x) return parent[x];
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int a = find(x);
		int b = find(y);
		
		if(a == b) return false;
		if(a < b) parent[b] = a;
		else parent[a] = b;
		
		return true;
	}
	
	static void init() {
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}
	}

}
