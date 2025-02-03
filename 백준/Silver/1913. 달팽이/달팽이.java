import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] board;
	static int[] dr = {-1, 0, 1, 0}; //상 우 하 좌
	static int[] dc = {0, 1, 0, -1}; //상 우 하 좌
	static int count;
	static int x, y;

	public static void main(String[] args) throws Exception{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		//초기화
		board = new int[N][N];
		count = 1;
		int r = N/2;
		int c = N/2;
		x = r;
		y = c;
		board[r][c] = count++;
		
		//로직
		for (int i = 0; i < N/2; i++) {
			//상
			for (int j = 0; j < 2*i+1; j++) {
				int nr = r+dr[0];
				int nc = c+dc[0];
				if(count == M) {
					x = nr;
					y = nc;
				}
				board[nr][nc] = count++;
				r = nr;
				c = nc;
			}
			//우
			for (int j = 0; j < 2*i+1; j++) {
				int nr = r+dr[1];
				int nc = c+dc[1];
				if(count == M) {
					x = nr;
					y = nc;
				}
				board[nr][nc] = count++;
				r = nr;
				c = nc;
			}
			//하
			for (int j = 0; j < 2*i+2; j++) {
				int nr = r+dr[2];
				int nc = c+dc[2];
				if(count == M) {
					x = nr;
					y = nc;
				}
				board[nr][nc] = count++;
				r = nr;
				c = nc;
			}
			//좌
			for (int j = 0; j < 2*i+2; j++) {
				int nr = r+dr[3];
				int nc = c+dc[3];
				if(count == M) {
					x = nr;
					y = nc;
				}
				board[nr][nc] = count++;
				r = nr;
				c = nc;
			}
		}
		//상
		for (int i = 0; i < N-1; i++) {
			int nr = r+dr[0];
			int nc = c+dc[0];
			if(count == M) {
				x = nr;
				y = nc;
			}
			board[nr][nc] = count++;
			r = nr;
			c = nc;
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(board[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
		System.out.println((x+1) + " " + (y+1));

	}

}
