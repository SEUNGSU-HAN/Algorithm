import java.io.*;
import java.util.*;


public class Main {
	static int N, M;
	static int[][] board;
	static Queue<int[]> shark;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[][] visited;
	static int maxD = 0;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][M];
		visited = new int[N][M];
		shark = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(visited[i], -1);
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) {
					shark.add(new int[] {i, j});
					visited[i][j] = 0;
				}
			}
		}
		
		/* 로직 */
		bfs();
		
		/* 출력 */
		System.out.println(maxD);
	}

	static void bfs() {
		while(!shark.isEmpty()) {
			int[] cur = shark.poll();
			int r = cur[0];
			int c = cur[1];
			for (int i = 0; i < 8; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(check(nr, nc) && visited[nr][nc] < 0) {
					if(board[nr][nc] == 0) {
						visited[nr][nc] = visited[r][c]+1;
						shark.offer(new int[] {nr, nc});
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				maxD = Math.max(maxD, visited[i][j]);
			}
		}
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < M);
	}

}
