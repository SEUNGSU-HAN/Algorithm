import java.util.*;
import java.io.*;

public class Main {
	static int R, C, count;
	static int[] dr = {-1, 0, 1};
	static char[][] board;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				char c = str.charAt(j);
				board[i][j] = c;
			}
		}
		
		/* 로직 */
		
		for(int i=0; i<R; i++) {
			backtracking(i, 0);			
		}
		
		/* 출력 */
		System.out.print(count);
	}

	static boolean backtracking(int r, int c) {			
		if(c == C-1) {
			board[r][c] = 'o';
			count++;
			return true;
		}
		
		board[r][c] = 'o';
		boolean isCan = true;
		
		for(int i=0; i<3; i++) {
			if(r+dr[i]<0 || r+dr[i]>=R) continue;
			if(board[r+dr[i]][c+1] != '.') continue;
			isCan = backtracking(r+dr[i], c+1);
			if(isCan) return true;
		}
		
		if(!isCan) board[r][c] = '.';
		
		return false;
	}


}
