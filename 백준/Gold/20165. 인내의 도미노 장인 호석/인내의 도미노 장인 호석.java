import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static class Domino {
		int h;
		boolean flag; //true -> up, false -> down;
		
		public Domino(int h, boolean flag) {
			this.h = h;
			this.flag = flag;
		}
	}
	static Domino[][] board;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		board = new Domino[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int h = Integer.parseInt(st.nextToken());
				board[i][j] = new Domino(h-1, true);
			}
		}
		
		
		/* 로직 */
		int sum = 0;
				
		while(R-- > 0) {
			//공격
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			String s = st.nextToken();

			sum += attack(r, c, convertToInteger(s));
			
			//수비
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			
			board[r][c].flag = true;
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		sb.append(sum).append("\n");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(board[i][j].flag ? "S " : "F ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	static int attack(int r, int c, int d) {
		int count = 1;
		
		int power = board[r][c].h;
		board[r][c].flag = false;
		
		while(power-- > 0) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(check(nr, nc)) {
				if(board[nr][nc].flag) {
					board[nr][nc].flag = false;
					count++;
					if(board[nr][nc].h > power) {
						power = board[nr][nc].h;
					}
				}
				
				r = nr;
				c = nc;
			}
		}
		
		return count;
		
	}
	
	static int convertToInteger(String s) {
		if(s.equals("N")) return 0;
		else if(s.equals("E")) return 1;
		else if(s.equals("S")) return 2;
		else return 3;
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < M);
	}

}
