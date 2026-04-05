import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;
	static int N, M;
	static int[][] board;
	static int[][][] dp;
	static int[] dc = {-1, 0, 1};


	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		board = new int[N][M];
		dp = new int[N][M][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				Arrays.fill(dp[i][j], INF);
			}
		}
		
		for(int j=0; j<M; j++) {
			for(int k=0; k<3; k++) {
				dp[0][j][k] = board[0][j];
			}
		}
		
		/* 로직 */
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int k=0; k<3; k++) {
					
					int prev_c = j - dc[k];
					
					if(check(i, prev_c)) {
						for(int p=0; p<3; p++) {
							if(k != p) {
								dp[i][j][k] = Math.min(dp[i][j][k], dp[i-1][prev_c][p] + board[i][j]);								
							}
						}
						
					}
				}
			}
		}

		
		/* 출력 */
		int min = INF;
		for(int j=0; j<M; j++) {
			for(int k=0; k<3; k++) {
				min = Math.min(min, dp[N-1][j][k]);
			}
		}
		System.out.print(min);
	}


	static boolean check(int nr, int prev_c) {
		return (0 <= nr && nr < N) && (0 <= prev_c && prev_c < M);
	}

}
