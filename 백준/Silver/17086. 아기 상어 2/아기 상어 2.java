import java.io.*;
import java.util.*;


public class Main {
	static int N, M;
	static int[][] board;
	static ArrayList<int[]> shark;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static boolean[][] visited;
	static int maxD = 0;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][M];
		shark = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) shark.add(new int[] {i, j});
			}
		}
		
		/* 로직 */
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] == 0) {
					visited = new boolean[N][M];
					bfs(new int[] {i, j, 0});
				}
			}
		}
		
		/* 출력 */
		System.out.println(maxD);
	}

	static void bfs(int[] start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		int distance = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 8; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc) && !visited[nr][nc]) {
					if(board[nr][nc] == 1) distance = Math.min(distance,  cur[2]+1);
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc, cur[2]+1});
				}
			}
		}
		maxD = Math.max(maxD, distance);
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < M);
	}

}
