import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 99999;
	static int N, M, R, max;
	static int[] items;
	static int[][] board;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(board[i], INF);
			board[i][i] = 0;
		}
		items = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			board[s][e] = w;
			board[e][s] = w;
		}
		
		/* 로직 */
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			int sum = items[i];
			for (int j = 0; j < N; j++) {
				if(board[i][j] == INF || board[i][j] > M || board[i][j] == 0) continue;
				sum += items[j];
			}
			max = Math.max(max, sum);
		}
		
		/* 출력 */
		System.out.print(max);
		
	}

}
