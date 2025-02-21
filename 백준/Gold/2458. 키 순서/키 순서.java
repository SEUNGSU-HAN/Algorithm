import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 999;
	static int N, M;
	static int[][] board;
	static int[][] reboard;
	
	static int[][] maxBoard;
	static int[][] remaxBoard;
	
	static int[][] resultBoard;
	static int result;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
			
		/* 초기화 */
		result = N;
		board = new int[N+1][N+1];
		reboard = new int[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = 1;
			reboard[c][r] = 1;
		}
		maxBoard = new int[N+1][N+1];
		remaxBoard = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i == j) maxBoard[i][j] = 0;
				else if(board[i][j] > 0) maxBoard[i][j] = board[i][j];
				else maxBoard[i][j] = INF;
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i == j) remaxBoard[i][j] = 0;
				else if(reboard[i][j] > 0) remaxBoard[i][j] = reboard[i][j];
				else remaxBoard[i][j] = INF;
			}
		}

		/* 로직 */
		//플루이드 워셜 활용
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					maxBoard[i][j] = Math.min(maxBoard[i][j], maxBoard[i][k]+maxBoard[k][j]);
					remaxBoard[i][j] = Math.min(remaxBoard[i][j], remaxBoard[i][k]+remaxBoard[k][j]);
				}
			}
		}
		//보드 종합
		resultBoard = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				resultBoard[i][j] = Math.min(maxBoard[i][j], remaxBoard[i][j]);
				if(resultBoard[i][j] == INF) {
					result--;
					break;
				}
			}
		}
		
		
		/* 출력 */
		System.out.print(result);
		
	}
}
