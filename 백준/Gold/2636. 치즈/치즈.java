import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board;
	static int[][] tmpBoard;	
	static boolean[][] visited;
	static ArrayList<int[]> cheese;
	static int cheeseCount;
	static int time;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][M];
		tmpBoard = new int[N][M];
		cheese = new ArrayList<>();
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				tmpBoard[i][j] = board[i][j];
				if(board[i][j] == 1) cheese.add(new int[] {i, j});
			}
		}
		
		/* 로직 */

		while(checkBoard(tmpBoard)) {
			time++;
			copyBoard();
			bfs(0);
			meltingCheese(0);
			melt();
		}

		
		/* 출력 */
		System.out.println(time + "\n" + cheeseCount);
	}

	private static void copyBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpBoard[i][j] = board[i][j];
			}
		}
	}

	private static void melt() {
		cheese = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(tmpBoard[i][j] == 3) {
					board[i][j] = 0;
				}else if(tmpBoard[i][j] == 1) {
					cheese.add(new int[] {i, j});
				}
			}
		}
	}

	private static void meltingCheese(int start) {
		for (int i = 0; i < cheese.size(); i++) {
			int r = cheese.get(i)[0];
			int c = cheese.get(i)[1];
			for (int j = 0; j < 4; j++) {
				int nr = r+dr[j];
				int nc = c+dc[j];
				if(tmpBoard[nr][nc] == 2) {
					tmpBoard[r][c] = 3;
				}
			}
		}
	}

	/**
	 * 치즈가 모두 녹아 없어지기 전인지 여부를 판단
	 * */
	private static boolean checkBoard(int[][] b) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(b[i][j] == 3) cheeseCount++;
				else if(b[i][j] == 1) {
					cheeseCount = 0;
					return true;
				}
			}
		}
		return false;
	}

	private static void bfs(int start) {
		//외부 공기 값 변경 0 -> 2
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		tmpBoard[0][0] = 2;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(check(nr, nc)) {
					visited[0][0] = true;
					tmpBoard[nr][nc] = 2;
					q.offer(new int[] {nr, nc});
				}
			}
		}

	}

	private static boolean check(int nr, int nc) {
		if((0 <= nr && nr < N) && (0 <= nc && nc < M)
				&& visited[nr][nc] == false
				&& tmpBoard[nr][nc] == 0) return true;
		return false;
	}

}

