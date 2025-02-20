import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] j, c; //주난, 초코바 위치
	static char[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

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
		for (int i = 0; i < N; i++) {
			char[] cl = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				board[i][j] = cl[j];
			}
		}
		visited = new boolean[N][M];
		
		/* 로직+출력 */
		bfs();
	}
	//수정후(개선 버전)
	private static void bfs() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {j[0], j[1], 0});
		visited[j[0]][j[1]] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == c[0] && cur[1] == c[1]) {
				System.out.print(cur[2]);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc)) {
					visited[nr][nc] = true;
					if(board[nr][nc] == '0') q.addFirst(new int[] {nr, nc, cur[2]});
					else q.offer(new int[] {nr, nc, cur[2]+1});
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return ((0 <= nr && nr < N) && (0 <= nc && nc < M) && !visited[nr][nc]);
	}

}
