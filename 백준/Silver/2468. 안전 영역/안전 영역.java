import java.io.*;
import java.util.*;
public class Main {
	static int N;
	static int[][] board;
	static int[][] temp;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[][] visited;
	static int maxHeight = 0;
	static int safeCount = 0;
	static int maxSafe = 0;

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
				maxHeight = Math.max(board[i][j], maxHeight);
			}
		}
		temp = new int[N][N];
		
		/* 로직 */
		for (int i = 0; i <= maxHeight; i++) {
			//비 내린당
			rain(i);
			deepCopy();
			//안정 역영 췤
			visited = new boolean[N][N];
			safeCount = 0;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(temp[j][k] != 0 && temp[j][k] > 0) {
						safeCount--;
						//FF
						bfs(new int[] {j, k});
					}
				}
			}
			//안전영역은 음수로 표시 (출력시에만 절댓값 변경)
			maxSafe = Math.min(maxSafe, safeCount);
		}
		
		/* 출력 */
		System.out.print(Math.abs(maxSafe));
	}

	static void deepCopy() {
		for (int i = 0; i < N; i++) {
			System.arraycopy(board[i], 0, temp[i], 0, board[i].length);
		}
	}

	static void rain(int r) {
		//r보다 작거나 같은 도시는 0으로 표시
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j] <= r) board[i][j] = 0;
			}
		}
	}

	static void bfs(int[] start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		temp[start[0]][start[1]] = safeCount;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc) && !visited[nr][nc]) {
					q.offer(new int[] {nr, nc});
					temp[nr][nc] = safeCount;
					visited[nr][nc] = true;
				}
			}
		}
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N) && board[nr][nc] != 0;
	}

}
