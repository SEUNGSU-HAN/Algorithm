import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//불 (불! 이랑 다른거임, 지훈 -> 상근 으로만 바꿈)
public class Main {
	static int T, R, C;
	static char[][] board;//-1:벽, 0:길, 1:불, 2:지훈
	static Queue<int[]> sanggeun;
	static Queue<int[]> fire;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean isEscape;
	static int time = 1;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			board = new char[R][C];
			sanggeun = new ArrayDeque<>();
			fire = new ArrayDeque<>();
			for (int i = 0; i < R; i++) {
				char[] cl = br.readLine().toCharArray();
				for (int j = 0; j < C; j++) {
					board[i][j] = cl[j];
					if(board[i][j] == '*') {
						fire.offer(new int[] {i, j});
					}
					if(board[i][j] == '@') {
						sanggeun.offer(new int[] {i, j});
					}
				}
			}
			isEscape = false;
			time = 1;
			
			/* 로직 */
			if(!((sanggeun.peek()[0] == 0 || sanggeun.peek()[0] == R-1)
					|| (sanggeun.peek()[1] == 0 || sanggeun.peek()[1] == C-1))) {
				loop:
					while(!sanggeun.isEmpty()) {
						int js = sanggeun.size();
						for (int i = 0; i < js; i++) {
							if(escape()) break loop;
						}
						time++;
						int fs = fire.size();
						for (int i = 0; i < fs; i++) {
							fire();
						}
					}
			}else isEscape = true;
			
			/* 출력 */
			StringBuilder sb = new StringBuilder();
			if(isEscape) sb.append(time);
			else sb.append("IMPOSSIBLE");
			System.out.println(sb);			
		}
	}

	private static void fire() {
		int[] f = fire.poll();
		//불 확산
		for (int i = 0; i < 4; i++) {
			int nr = f[0]+dr[i];
			int nc = f[1]+dc[i];
			if(((0<=nr&&nr<R) && (0<=nc&&nc<C)) && board[nr][nc] != '#' && board[nr][nc] != '*') {
				board[nr][nc] = '*';
				fire.offer(new int[] {nr, nc});
			}
		}
	}

	private static boolean escape() {
		int[] j = sanggeun.poll();
		if(board[j[0]][j[1]] != '*') {
			if(j[0] == 0 || j[0] == R-1 || j[1] == 0 || j[1] == C-1) {
				isEscape = true;
				return true;
			}
			//지훈이 이동
			for (int i = 0; i < 4; i++) {
				int nr = j[0]+dr[i];
				int nc = j[1]+dc[i];
				if(board[nr][nc] == '.') {
					board[nr][nc] = '@';
					sanggeun.offer(new int[] {nr, nc});
				}
			}
		}
		return false;
	}

}
