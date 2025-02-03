import java.util.Scanner;

public class Main {
	static int n;
	static int[][] board;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		board = new int[n][n];
		StringBuilder sb = new StringBuilder();
		
		star(0, 0, n); //0, 0에서 길이n
		
		sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(board[i][j] == 1) sb.append("*");
				else sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

	private static void star(int x, int y, int n) {
		if(n == 3) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(i == 1 && j == 1) continue;
					board[x+i][y+j] = 1;
				}
			}
		}else {
			for (int i = 0; i < n; i+=(n/3)) {
				for (int j = 0; j < n; j+=(n/3)) {
					if(i == n/3 && j == n/3) continue;
					star(x+i, y+j, n/3);
				}
			}
		}
	}

}
