import java.io.*;
import java.util.*;

public class Main {
	static int N, set=0; //set: 현대 파이프가 놓인 상태(0:우,1:하,2:대)
	static int[][][] d = {
			{{0, 1}, {0, 0}, {1, 1}}, //우로 놓였을 때
			{{0, 0}, {1, 0}, {1, 1}}, //하로 놓였을 때
			{{0, 1}, {1, 0}, {1, 1}} //대각선으로 놓였을 때
	};
	static int[][] board;
	static long[][][] dp;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new long[3][N][N];
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < N; j++) {
//				Arrays.fill(dp[i][j], -1);
//			}
//		}
		
		/* 로직+출력 */
		System.out.print(dfs(0, 1, 0)); //r, c, 파이프 상태		
		
	}

	static int dfs(int r, int c, int s) {
		if(r == N-1 && c == N-1) return 1;
		
		int count = 0;
		for (int i = 0; i < 3; i++) {
			int nr = r+d[s][i][0];
			int nc = c+d[s][i][1];
			if(nr == r && nc == c) continue;
			if(check(nr, nc) && board[nr][nc] != 1) {
				if(i == 2) //대각선으로 갈 경우 현재 위치에서 우,하,대 체크
					if(!check2(r, c)) continue;
				count += dfs(nr, nc, i);
			}
		}
		return count;
	}

	static boolean check2(int r, int c) {
		for (int i = 0; i < d[2].length; i++) {
			int nr = r+d[2][i][0];
			int nc = c+d[2][i][1];
			if(board[nr][nc] == 1) return false;
		}
		return true;
	}
	
	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
