import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] board;
	static long[][] dp;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][M];
		dp = new long[N+1][M+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				dp[i+1][j+1] = board[i][j];
			}
		}
		
		/* 로직 */
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = Collections.max(Arrays.asList(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]))
							+ board[i-1][j-1];
			}
		}
		
		/* 출력 */
		System.out.print(dp[N][M]);

	}

}
