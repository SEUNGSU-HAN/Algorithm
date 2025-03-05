import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static class Edge implements Comparable<Edge>{
		int s, e, w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	static PriorityQueue<Edge> points;
	static int[] p, r;
	static long min = 0L;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		StringTokenizer st;
		points = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			points.offer(new Edge(s, e, w));
		}
		
		/* 로직 */
		init();
		int cnt = 0;
		while(cnt != N-1) {
			Edge edge = points.poll();
			if(union(edge.s, edge.e)) {
				cnt++;
				min += edge.w;
			}
		}
		
		
		/* 출력 */
		System.out.print(min);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false;
		if(r[x] < r[y]) {
			r[y] += r[x];
			p[x] = y;
		}else {
			r[x] += r[y];
			p[y] = x;
		}
		return true;
	}

	static int find(int x) {
		if(x == p[x]) return p[x];
		else return p[x] = find(p[x]);
	}

	static void init() {
		p = new int[N+1];
		r = new int[N+1];
		for (int i = 0; i <= N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}

}
