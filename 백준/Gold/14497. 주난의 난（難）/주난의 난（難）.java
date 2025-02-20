import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] j, c; //주난, 초코바 위치
	static char[][] board;
	static char[][] tmpBoard;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean isCatch;
	static int jump;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		j = new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
		c = new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
		
		/* 초기화 */
		board = new char[N][M];
		tmpBoard = new char[N][M];
		for (int i = 0; i < N; i++) {
			char[] cl = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				board[i][j] = cl[j];
				tmpBoard[i][j] = cl[j];
			}
		}
		
		/* 로직 */
		while(!isCatch) {
			visited = new boolean[N][M];
			jump++;
			bfs();
		}
		
		/* 출력 */
		System.out.print(jump);
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(j);
		visited[j[0]][j[1]] = true;
		loop:
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc)) {
					if(board[nr][nc] == '1') board[nr][nc] = '0';
					else if(board[nr][nc] == '0') q.offer(new int[] {nr, nc});
					else {
						isCatch = true;
						break loop;
					}
					visited[nr][nc] = true;
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return ((0 <= nr && nr < N) && (0 <= nc && nc < M) && !visited[nr][nc]);
	}

}
