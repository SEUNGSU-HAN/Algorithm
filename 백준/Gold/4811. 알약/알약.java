import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[][] board;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* 초기화 */
		board = new long[31][31];
		Arrays.fill(board[0], 1);
		for (int i = 0; i <= 30; i++) {
			board[1][i] = i+1;
		}
		
		/* 로직 */
		for (int i = 2; i <= 30; i++) {
			for (int j = 0; j <= 31-i; j++) {
				if(j > 0) board[i][j] = board[i][j-1]+board[i-1][j+1];
				else board[i][j] = board[i-1][j+1];
			}
		}
		StringBuilder sb = new StringBuilder();
		String str = "";
		while((str = br.readLine().trim()) != null && !str.isEmpty() && !str.equals("0")) {
			int n = Integer.parseInt(str);
			sb.append(board[n][0]).append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
