import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static class Edge {
		int s, e, w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

	}
	static int[] p;
	static long min = 0L;
	static long max = 0L;
	static PriorityQueue<Edge> minPoints;
	static PriorityQueue<Edge> maxPoints;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		minPoints = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1.w, o2.w);
		});
		maxPoints = new PriorityQueue<>((o1, o2) -> {
			return -Integer.compare(o1.w, o2.w);
		});
		
		for (int i = 0; i < M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			minPoints.offer(new Edge(s, e, w == 0 ? 1 : 0));
			maxPoints.offer(new Edge(s, e, w == 0 ? 1 : 0));
		}
		
		/* 로직 */
		//크루스칼 사용
		init();
		runMin();
		init();
		runMax();
		
		/* 출력 */
		System.out.print(max*max-min*min);
	}

	static void runMax() {
		while(!maxPoints.isEmpty()) {
			Edge edge = maxPoints.poll();
			if(maxUnion(edge.s, edge.e)) {
				max += edge.w;
			}
		}
	}

	static void runMin() {
		while(!minPoints.isEmpty()) {
			Edge edge = minPoints.poll();
			if(minUnion(edge.s, edge.e)) {
				min += edge.w;
			}
		}
	}
	static boolean maxUnion(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false;
		if(x < y) p[x] = y;
		else p[y] = x;
		
		return true;
	}

	static boolean minUnion(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false;
		if(x < y) p[y] = x;
		else p[x] = y;
		
		return true;
	}

	static int find(int x) {
		if(p[x] == x) return p[x];
		else return p[x] = find(p[x]);
	}

	static void init() {
		p = new int[N+1];
		for (int i = 0; i <= N; i++) {
			p[i] = i;
		}
	}

}
