import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] board;
	static ArrayList<Integer> rDiagonal;
	static ArrayList<Integer> cantCol;
	static int count;
	
	public static void main(String[] args) {
		/* 입력 */
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		/* 초기화 */
		board = new int[N][N];
		rDiagonal = new ArrayList<>();
		cantCol = new ArrayList<>();
		
		/* 로직 */
		queen(0, 0);

		
		/* 출력 */
		System.out.println(count);
	}

	private static void queen(int r, int q) {
		//퀸을 모두 놓았을 때
		if(q == N) {
			count++;
			return;
		}
		
		for (int c = 0; c < N; c++) {
			if(isValid(r, c)) {
				q++; //퀸 배치
				board[r][c] =1;
				cantCol.add(c);
				rDiagonal.add(r+c);
				queen(r+1, q); //다음 퀸 배치
				q--;
				board[r][c] = 0;
				cantCol.remove(cantCol.size()-1);
				rDiagonal.remove(rDiagonal.size()-1);
			}
		}
		
	}

	private static boolean isValid(int r, int c) {
		if(!cantCol.contains(c) && !rDiagonal.contains(r+c)) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < N; j++) {
					if(board[i][j] == 1) {
						if(c == j) return false;
						if(Math.abs(r-i) == Math.abs(c-j)) return false;
					}
				}
			}
			return true;
		}
		return false;
	}
}
