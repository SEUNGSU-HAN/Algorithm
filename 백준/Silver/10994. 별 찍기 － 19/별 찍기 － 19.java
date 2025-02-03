import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static char[][] board;
	
	public static void main(String[] args) throws Exception{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		//초기화
		int boardSize = N+3*(N-1);
		board = new char[boardSize][boardSize];
		Arrays.stream(board).forEach(i -> Arrays.fill(i, ' '));
		
		//로직
		box(0, 0, boardSize);
		
		
		//출력
		for (int j = 0; j < boardSize; j++) {
			sb.append(board[j]).append('\n');
		}
		System.out.println(sb.toString());
	}

	private static void box(int r, int c, int size) {
		if(size == 1) {
			board[r][c] = '*';
			return;
		}
		else {
			for (int i = r; i < r+size; i++) {
				for (int j = c; j < c+size; j++) {
					if(i == r || i == r+size-1) board[i][j] = '*';
					else if(j == c || j == c+size-1) board[i][j] = '*';
				}
			}
			box(r+2, c+2, size-4);
		}
		
	}

}
