import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
	static int N;
	static char[][] board;
	
	public static void main(String[] args) throws Exception{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		//초기화
		int rSize = 1;
		int cSize = 1;
		if(N > 1) {
			rSize = (int)Math.pow(2, N)-1;
			for (int i = 2; i <= N; i++) {
				cSize += (int)Math.pow(2, i);
			}
		}
		board = new char[rSize][cSize];
		Arrays.stream(board).forEach(i -> Arrays.fill(i, ' '));
		
		//로직
		triangle((N%2 == 1) ? 0 : rSize-1, cSize/2, rSize, cSize, N);
		
		//출력
		for (int i = 0; i < rSize; i++) {
			if(N%2 == 1) {
				for (int j = 0; j <= cSize/2+i; j++) {
					sb.append(board[i][j]);
				}
			}else {
				for (int j = 0; j < cSize-i; j++) {
					sb.append(board[i][j]);
				}
			}
			sb.append(" " + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void triangle(int r, int c, int rs, int cs, int n) {
		if(n == 1) {
			board[r][c] = '*';
			return;
		}
		//n -> 홀수: up, 짝수: down
		//(up: 삼각형 그림, down: 역삼각형)
		if(n%2 == 0) {
			for (int i = 0; i < rs; i++) {
				if(i != rs-1) {
					//사이드 별
					board[r-i][c+i] = '*';
					board[r-i][c-i] = '*';
				}else {
					//all 별
					for (int j = cs/2; j >= 0; j--) {
						board[r-i][c+j] = '*';
						board[r-i][c-j] = '*';
					}
				}
			}
			triangle(r-(rs-2), c, rs/2, cs/2-1, n-1);
		}else {
			for (int i = 0; i < rs; i++) {
				if(i != rs-1) {
					//사이드 별
					board[r+i][c+i] = '*';
					board[r+i][c-i] = '*';
				}else {
					//all 별
					for (int j = cs/2; j >= 0; j--) {
						board[r+i][c+j] = '*';
						board[r+i][c-j] = '*';
					}
				}
			}
			triangle(r+(rs-2), c, rs/2, cs/2-1, n-1);
		}
		
	}

}
