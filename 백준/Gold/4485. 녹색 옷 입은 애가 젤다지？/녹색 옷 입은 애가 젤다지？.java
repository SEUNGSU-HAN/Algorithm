import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 999;
	static int N, test_case = 1;
	static int[][] board;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] dp;

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
			dp = new int[N*N];
			Arrays.fill(dp, INF);
			
			/* 로직 */
			//1. 다익스트라
			PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
			int[] start = {0, 0, board[0][0]};
			q.offer(start);
			dp[0] = board[0][0];
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur[0]+dr[i];
					int nc = cur[1]+dc[i];
					if(check(nr, nc)) {
						int pre = cur[0]*N+cur[1]%N;
						int next = nr*N+nc%N;
						if(dp[next] > dp[pre] + board[nr][nc]) {
							dp[next] = dp[pre] + board[nr][nc];
							q.offer(new int[] {nr, nc, board[nr][nc]});
						}
					}
				}
			}
			sb.append("Problem ").append(test_case++).append(": ").append(dp[N*N-1]).append("\n");
			N = Integer.parseInt(br.readLine().trim());
		}
		/* 출력 */
		System.out.print(sb);
		
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
