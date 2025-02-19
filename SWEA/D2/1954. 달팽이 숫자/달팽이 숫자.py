import java.util.Scanner;

public class Solution {
	static int T, N;
	static int r, c;
	static int[][] board;
	static int[] dr = {0, 1, 0, -1}; //우하좌상
	static int[] dc = {1, 0, -1, 0};
	static int direct;
	static int count;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			/* 입력 */
			N = sc.nextInt();
			
			/* 초기화 */
			board = new int[N][N];
			r = 0; c = 0;
			direct = 0;
			count = 1;
			
			/* 로직 */
			while(count <= N*N) {
				if(board[r][c] == 0) board[r][c] = count++;
				int nr = r+dr[direct%4];
				int nc = c+dc[direct%4];
				while(check(nr, nc)) {
					board[nr][nc] = count++;
					r = nr;
					c = nc;
					nr = r+dr[direct%4];
					nc = c+dc[direct%4];
				}
				direct++;
			}
			
			/* 출력 */
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(board[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.print(sb);
		}
		
		sc.close();
	}

	private static boolean check(int nr, int nc) {
		return ((0 <= nr && nr < N) && (0 <= nc && nc < N) && board[nr][nc] == 0);
	}
}
