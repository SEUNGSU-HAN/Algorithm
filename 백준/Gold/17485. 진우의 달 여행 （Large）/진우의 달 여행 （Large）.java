import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;
	static int N, M, result = INF;
	static int[] dc = {-1, 0, 1};
	static int[][] board;
	static int[][][] dp;
	static class Rocket { 
		int r, c, w, pre;
		
		public Rocket(int r, int c, int w, int pre) {
			this.r = r;
			this.c = c;
			this.w = w;
			this.pre = pre;
		}
	}

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		board = new int[N][M];
		dp = new int[3][N][M];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int x = Integer.parseInt(st.nextToken());
				board[i][j] = x;
			}
		}
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<N; j++ ) {
				Arrays.fill(dp[i][j], INF);
			}
		}
		
		/* 로직 */
		for (int j = 0; j < M; j++) {
            dp[0][0][j] = board[0][j];
            dp[1][0][j] = board[0][j];
            dp[2][0][j] = board[0][j];
        }
		
		for (int i = 1; i < N; i++) {
		    for (int j = 0; j < M; j++) {
		        for (int k = 0; k < 3; k++) { 
		            
		            int prev = j - dc[k]; 
		            
		            if (check(i - 1, prev)) {
		                for (int p = 0; p < 3; p++) {
		                    if (k != p) { 
		                    	dp[k][i][j] = Math.min(dp[k][i][j], dp[p][i - 1][prev] + board[i][j]);		                    }
		                }
		            }
		        }
		    }
		}
		
		
		/* 출력 */
		int result = INF;
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < 3; k++) {
                result = Math.min(result, dp[k][N - 1][j]); // 마지막 행(N-1)의 최솟값 찾기
            }
        }
        
        System.out.print(result);
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < M);
	}

}
