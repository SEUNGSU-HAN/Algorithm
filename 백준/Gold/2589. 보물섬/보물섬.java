import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//보물섬
public class Main {
	static int N, M;
	static int[][] board;
	static boolean[][] visited;
	static ArrayList<int[]> land;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int minDis;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][M];
		land = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			char[] cl = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(cl[j] == 'L') {
					board[i][j] = 0;
					land.add(new int[] {i, j});
				}
				else board[i][j] = -1;
			}
		}
		
		/* 로직 */
		for (int i = 0; i < land.size(); i++) {
			visited = new boolean[N][M];
			bfs(i, 0);
		}
		
		/* 출력 */
		System.out.print(minDis);
	}

	private static void bfs(int start, int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		int[] sp = {land.get(start)[0], land.get(start)[1], 0};
		q.offer(sp);
		visited[sp[0]][sp[1]] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			minDis = Math.max(minDis, cur[2]);
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc)) {
					q.offer(new int[] {nr, nc, cur[2]+1});
					visited[nr][nc] = true;
				}
			}
		}
		
	}

	private static boolean check(int nr, int nc) {
		return ((0 <= nr && nr < N) && (0 <= nc && nc < M) 
				&& !visited[nr][nc]
				&& board[nr][nc] != -1);
	}

}
