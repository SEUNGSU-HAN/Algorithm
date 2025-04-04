import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[][] board;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N+1][N+1];
		board[1][1] = 1;
		
		/* 로직 */
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				board[i][j] = board[i-1][j-1]+board[i-1][j];
			}
		}
		
		/* 출력 */
		System.out.print(board[N][K]);
	}

}
