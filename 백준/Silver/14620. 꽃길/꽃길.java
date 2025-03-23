import java.io.*;
import java.util.*;

public class Main {
	static int N, IN, minPrice=Integer.MAX_VALUE;
	static int[][] board;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] flowers;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		IN = N-2;
		
		/* 초기화 */
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		flowers = new int[3];
		visited = new boolean[N][N];
		
		/* 로직 */
		combi(0, 0, 0);
		
		/* 출력 */
		System.out.print(minPrice);
	}

	static void combi(int cnt, int start, int tot) {
		if(tot >= minPrice) return;
		if(cnt == 3) {
			minPrice = Math.min(tot, minPrice);
			return;
		}
		for (int i = start; i < IN*IN; i++) {
			int r = i/IN+1;
			int c = i%IN+1;
			if(check(r, c) || visited[r][c]) continue;
			flowers[cnt] = i;
			visited[r][c] = true;
			for (int j = 0; j < 4; j++) {
				int nr = r+dr[j];
				int nc = c+dc[j];
				visited[nr][nc] = true;
				tot += board[nr][nc];
			}
			combi(cnt+1, i+1, tot+board[r][c]);
			visited[r][c] = false;
			for (int j = 0; j < 4; j++) {
				int nr = r+dr[j];
				int nc = c+dc[j];
				visited[nr][nc] = false;
				tot -= board[nr][nc];
			}
		}
	}

	static boolean check(int r, int c) {
		for (int j = 0; j < 4; j++) {
			int nr = r+dr[j];
			int nc = c+dc[j];
			if(visited[nr][nc]) return true;
		}
		return false;
	}

}
