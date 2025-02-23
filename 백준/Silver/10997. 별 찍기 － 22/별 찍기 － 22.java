import java.util.*;

public class Main {
	static int N , R, C;
	static boolean[][] board;
	static int[] dr = {-1, 0, 1, 0}; //하좌상우
	static int[] dc = {0, -1, 0, 1};
	static int r, c;
	
	public static void main(String[] args) {
		/* 입력 */
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		/* 초기화 */

		R = 4*N-1; C = 4*N-3;
		board = new boolean[R][C];
		
		/* 로직 */
		//시작점
		if(N == 1) board[0][0] = true;
		else star(2, C-2, R, C, N);
			
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			String spaceBuffer = "";
			for (int j = 0; j < C; j++) {
				if(board[i][j]) {
					sb.append(spaceBuffer).append("*");
					spaceBuffer="";
				}
				else spaceBuffer+=" ";
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void star(int sy, int sx, int r, int c, int cnt) {
		int y = sy, x = sx;
		
		if(cnt == 1) {
			board[y][x] = true;
			return;
		}
		
		board[y][x] = true;
		board[y][++x] = true;

		//하
		for (int j = 0; j < r-3; j++) {
			board[++y][x] = true;
		}
		//좌
		for (int j = 0; j < c-1; j++) {
			board[y][--x] = true;
		}
		//상
		for (int j = 0; j < r-1; j++) {
			board[--y][x] = true;
		}
		//우
		for (int j = 0; j < c-1; j++) {
			board[y][++x] = true;
		}
		
		
		if(cnt == 2) {
			board[sy][sx-1] = true;
			board[sy+1][sx-1] = true;
			star(sy+2, sx-1, 1, 1, cnt-1);
		}else star(sy+2, sx-2, r-4, c-4, cnt-1);
		
	}

}
