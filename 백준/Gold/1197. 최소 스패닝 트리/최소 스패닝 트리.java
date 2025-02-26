import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	
	static class Edge implements Comparable<Edge>{
		int s, e, w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			//가중치 기준으로 우선순위 정렬
			return Integer.compare(this.w, o.w);
		}
		
	}
	static PriorityQueue<Edge> points;
	static int[] p, r;
	static long min = 0L;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		points = new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			points.offer(new Edge(s, e, w));
		}
		
		/* 로직 */
		init();
		int cnt = 0;
		while(cnt != V-1) {
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
		p = new int[V+1];
		r = new int[V+1];
		for (int i = 0; i < V+1; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}

}
