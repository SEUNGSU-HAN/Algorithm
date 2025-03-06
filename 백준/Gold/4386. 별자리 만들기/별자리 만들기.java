import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static double result;
	static class Star {
		double x, y;

		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static class Edge implements Comparable<Edge>{
		int v;
		double w;

		public Edge(int v, double w) {
			this.v = v;
			this.w = w;
		}

		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}
	static Star[] stars;
	static double[][] board;
	static PriorityQueue<Edge> zodiac;
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		zodiac = new PriorityQueue<>();
		stars = new Star[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(x, y);
		}
		visited = new boolean[N];
		board = new double[N][N];
		
		/* 로직 */
		//각 별들의 간선을 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j) continue;
				if(board[i][j] != 0.0) {
					board[j][i] = board[i][j];
				}else {
					double d = distance(stars[i].x, stars[i].y, stars[j].x, stars[j].y);
					board[i][j] = 	d;
				}
			}
		}
		
		//Use Prim (시작은 0번 노드부터)
		zodiac.offer(new Edge(0, 0));
		while(!zodiac.isEmpty()) {
			Edge edge = zodiac.poll();
			int s = edge.v;
			if(visited[s]) continue;
			visited[s] = true;
			result += edge.w;
			for (int i = 0; i < N; i++) {
				if(visited[i] || s == i) continue;
				zodiac.offer(new Edge(i, board[s][i]));
			}
		}
		
		/* 출력 */
		System.out.printf("%.2f", result);
	}

	static double distance(double x, double y, double x2, double y2) {
		double dx = Math.abs(x2-x);
		double dy = Math.abs(y2-y);
		return Math.sqrt((dx*dx)+(dy*dy));
	}

}
