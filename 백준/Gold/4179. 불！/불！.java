import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//불!
public class Main {
	static int R, C;
	static char[][] board;//-1:벽, 0:길, 1:불, 2:지훈
	static Queue<int[]> jihun;
	static Queue<int[]> fire;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean isEscape;
	static int time = 1;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new char[R][C];
		jihun = new ArrayDeque<>();
		fire = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			char[] cl = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				board[i][j] = cl[j];
				if(board[i][j] == 'F') {
					fire.offer(new int[] {i, j});
				}
				if(board[i][j] == 'J') {
					jihun.offer(new int[] {i, j});
				}
			}
		}
		
		/* 로직 */
		if(!((jihun.peek()[0] == 0 || jihun.peek()[0] == R-1)
				|| (jihun.peek()[1] == 0 || jihun.peek()[1] == C-1))) {
			loop:
			while(!jihun.isEmpty()) {
				int js = jihun.size();
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
		System.out.print(isEscape ? time: "IMPOSSIBLE");		
	}

	private static void fire() {
		int[] f = fire.poll();
		//불 확산
		for (int i = 0; i < 4; i++) {
			int nr = f[0]+dr[i];
			int nc = f[1]+dc[i];
			if(((0<=nr&&nr<R) && (0<=nc&&nc<C)) && board[nr][nc] != '#' && board[nr][nc] != 'F') {
				board[nr][nc] = 'F';
				fire.offer(new int[] {nr, nc});
			}
		}
	}

	private static boolean escape() {
		int[] j = jihun.poll();
		if(board[j[0]][j[1]] != 'F') {
			if(j[0] == 0 || j[0] == R-1 || j[1] == 0 || j[1] == C-1) {
				isEscape = true;
				return true;
			}
			//지훈이 이동
			for (int i = 0; i < 4; i++) {
				int nr = j[0]+dr[i];
				int nc = j[1]+dc[i];
				if(board[nr][nc] == '.') {
					board[nr][nc] = 'J';
					jihun.offer(new int[] {nr, nc});
				}
			}
		}
		return false;
	}

}
