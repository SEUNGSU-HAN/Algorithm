import java.io.*;
import java.util.*;

public class Solution {
	static final int INF = 999;
	static int T, N;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] board, dp;
	static class Pos implements Comparable<Pos>{
		int r, c, w;

		public Pos(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			N = Integer.parseInt(br.readLine());
			
			/* 초기화 */
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine().trim();
				for (int j = 0; j < N; j++) {
					board[i][j] = str.charAt(j)-'0';
				}
			}
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], INF);
			}
			
			/* 로직 */
			PriorityQueue<Pos> q = new PriorityQueue<>();
			q.offer(new Pos(0, 0, board[0][0]));
			while(!q.isEmpty()) {
				Pos cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur.r+dr[i];
					int nc = cur.c+dc[i];
					if(check(nr, nc)) {
						if(dp[nr][nc] > cur.w + board[nr][nc]) {
							dp[nr][nc] = cur.w+board[nr][nc];
							q.offer(new Pos(nr, nc, dp[nr][nc]));
						}
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(dp[N-1][N-1]).append("\n");
		}
		/* 출력 */
		System.out.print(sb);
		
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
