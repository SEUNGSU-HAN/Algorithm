import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 999;
	static int N, test_case = 1;
	static int[][] board;
	static int[][] dp;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static class Node implements Comparable<Node>{
		int r, c, w;

		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
				
		while(N != 0) {
			/* 초기화 */
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dp = new int[N][N];
			for(int i = 0; i < N; i++) {
				Arrays.fill(dp[i], INF);
			}
			
			/* 로직 */
			//1. 다익스트라
			
			//개선 ver
			PriorityQueue<Node> q = new PriorityQueue<>();
			Node start = new Node(0, 0, board[0][0]);
			q.offer(start);
			dp[0][0] = board[0][0];
			while(!q.isEmpty()) {
				Node cur = q.poll();
				
				if(cur.r == N-1 && cur.c == N-1) break;
				
				for (int i = 0; i < 4; i++) {
					int nr = cur.r+dr[i];
					int nc = cur.c+dc[i];
					if(check(nr, nc)) {
						if(dp[nr][nc] > dp[cur.r][cur.c]+board[nr][nc]){
							dp[nr][nc] = dp[cur.r][cur.c]+board[nr][nc];
							q.offer(new Node(nr, nc, dp[nr][nc]));
						}
					}
				}
			}
			
			sb.append(String.format("Problem %d: %d\n", test_case++, dp[N-1][N-1]));
			
			N = Integer.parseInt(br.readLine().trim());
		}
		/* 출력 */
		System.out.print(sb);
		
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
