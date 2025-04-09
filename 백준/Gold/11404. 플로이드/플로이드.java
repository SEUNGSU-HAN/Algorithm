import java.io.*;
import java.util.*;

public class Main {
	static final long INF = 100_000*(100-1)+1;
	static int N, M;
	static long[][] board;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
				
		/* 초기화 */
		board = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = i == j ? 0 : INF;
			}
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			board[s][e] = board[s][e] == INF ? w : Math.min(board[s][e], w);
		}
		
		/* 로직 */
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
				}
			}
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(board[i][j] == INF ? 0 : board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
