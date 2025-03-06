import java.io.*;
import java.util.*;

public class Main {
	static int N, blue, white;
	static int[][] board;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/* 로직 */
		recur(0, 0, N);
		
		/* 출력 */
		System.out.println(white+"\n"+blue);
	}

	static void recur(int sr, int sc, int size) {
		if(size == 1) {
			if(board[sr][sc] == 1) blue++;
			else white++;
			return;
		}
		int sum = 0;
		for (int i = sr; i < sr+size; i++) {
			for (int j = sc; j < sc+size; j++) {
				sum += board[i][j];
			}
		}
		if(sum == 0) white++;
		else if(sum == size*size) blue++;
		else {
			int half = size/2;
			//1번 경우
			recur(sr, sc, half);
			//2번 경우
			recur(sr, sc+half, half);
			//3번 경우
			recur(sr+half, sc, half);
			//4번 경우
			recur(sr+half, sc+half, half);
		}
		
	}

}
