import java.util.*;

public class Main {
	static int N , R, C;
	static char[][] board;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int r, c;
	
	public static void main(String[] args) {
		/* 입력 */
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		/* 초기화 */
		if(N == 1) sb.append("*");
		else {
			R = 4*N-1; C = 4*N-3;
			board = new char[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					board[i][j] = ' ';
				}
				
			}
			
			/* 로직 */
			//시작점
			r = R-R/2; c = C/2;
			board[r][c] = '*';
			int star = 0;// 그릴 별의 개수
			loop:
			while(true) {
				star += 2;
				//상
				for (int i = 0; i < star; i++) {
					int nr = r+dr[0];
					int nc = c+dc[0];
					if(check(nr, nc)) {
						board[nr][nc] = '*';
						r = nr;
						c = nc;
					}
				}
				//우 (end point check)
				for (int i = 0; i < star; i++) {
					int nr = r+dr[1];
					int nc = c+dc[1];
					if(check(nr, nc)) {
						board[nr][nc] = '*';
						r = nr;
						c = nc;
					}else break loop;
				}
				star += 2;
				//하
				for (int i = 0; i < star; i++) {
					int nr = r+dr[2];
					int nc = c+dc[2];
					if(check(nr, nc)) {
						board[nr][nc] = '*';
						r = nr;
						c = nc;
					}
				}
				//좌
				for (int i = 0; i < star; i++) {
					int nr = r+dr[3];
					int nc = c+dc[3];
					if(check(nr, nc)) {
						board[nr][nc] = '*';
						r = nr;
						c = nc;
					}
				}
			}
			
			for (int i = 0; i < R; i++) {
				String spaceBuffer = "";
				for (int j = 0; j < C; j++) {
					if(board[i][j] == ' ') spaceBuffer += " ";
					else {
						sb.append(spaceBuffer).append(board[i][j]);
						spaceBuffer = "";
					}
				}
				sb.append("\n");
			}
			
		}
		
		/* 출력 */
		System.out.print(sb);
	}

	private static boolean check(int nr, int nc) {
		return (0<=nr && nr<R) && (0<=nc && nc<C);
	}

}
