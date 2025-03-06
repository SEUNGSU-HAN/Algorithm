import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static class Kedge implements Comparable<Kedge> {
		int s, e;
		long w;

		public Kedge(int s, int e, long w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Kedge o) {
			return Long.compare(this.w, o.w);
		}		
	}
	static class Pedge implements Comparable<Pedge> {
		int v;
		long w;

		public Pedge(int v, long w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Pedge o) {
			return Long.compare(this.w, o.w);
		}	
		
	}
//	static long[][] map;
	static int[] p;
	static PriorityQueue<Kedge> kuru_road;
//	static PriorityQueue<Pedge> prim_road;
//	static boolean[] pVisited;
	

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		kuru_road = new PriorityQueue<>();
//		prim_road = new PriorityQueue<>();
//		map = new long[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			long w = Integer.parseInt(st.nextToken());
//			map[s][e] = w;
//			map[e][s] = w;
			Kedge kedge = new Kedge(s, e, w);
			kuru_road.offer(kedge);
		}
//		pVisited = new boolean[N+1];
		
		/* 로직 */
		/*========Use Prim ver========*/
		//프림 알고리즘을 사용하여 모든 노드의 최소 연결 엣지를 찾음
		//간선 가중치들의 합을 저장해 두었다가
		//완성된 경로에서 가장 가중치가 높은 간선을 제거 해줌
		/*
		prim_road.offer(new Pedge(1, 0));
		long result = 0;
		int count = 0;
		long maxW = 0;
		while(!prim_road.isEmpty()) {
			Pedge edge = prim_road.poll();
			int v = edge.v;
			if(pVisited[v]) continue;
			result += edge.w;
			maxW = Math.max(maxW, edge.w);
			pVisited[v] = true;
			if(N == ++count) break;
			//현재 노드와 연결되있는 모든 노드의 간선 추가
			//간선 값 적은 순 우선 정렬됨
			for (int i = 1; i <= N; i++) {
				if(v == i || pVisited[i] || map[v][i] == 0) continue;
				prim_road.offer(new Pedge(i, map[v][i]));
			}
		}
		result -= maxW;
		*/
		
		
		/*========Use Kruskal ver========*/
		p = new int[N+1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
		long result = 0;
		long maxW = 0;
		int count = 0;
		while(!kuru_road.isEmpty() && count < N-1) {
			Kedge edge = kuru_road.poll();
			if(union(edge.s, edge.e)) {
				maxW = Math.max(maxW, edge.w);
				count++;
				result += edge.w;
			}
		}
		//가장 가중치가 큰 간선을 제거
		result -= maxW;
		
		
		
		/* 출력 */
		System.out.print(result);
	}


	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false;
		if(x < y) p[y] = x;
		else p[x] = y;
		return true;
	}


	static int find(int x) {
		if(x == p[x]) return p[x];
		else return p[x] = find(p[x]);
	}

}
