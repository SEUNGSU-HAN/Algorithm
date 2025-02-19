import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//토마토
public class Main {
	static int N, M;
	static int[][] board;
	static Queue<int[]> tomato;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int ripe, cantomato;
	static int minDay = -1;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][M];
		tomato = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) {
					ripe++;
					cantomato++;
					tomato.offer(new int[] {i, j});
				}else if(board[i][j] == 0) cantomato++;
			}
		}
		
		/* 로직 */
		while(!tomato.isEmpty()) {
			int len = tomato.size();
			for (int i = 0; i < len; i++) {
				spread();
			}
			minDay++;
		}
		
		/* 출력 */
		System.out.print((ripe == cantomato) ? minDay : -1);
	}

	private static void spread() {
		int[] t = tomato.poll();
		for (int i = 0; i < 4; i++) {
			int nr = t[0]+dr[i];
			int nc = t[1]+dc[i];
			if(check(nr, nc)) {
				board[nr][nc] = 1;
				ripe++;
				tomato.add(new int[] {nr, nc});
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return ((0 <= nr && nr < N) && (0 <= nc && nc < M) && board[nr][nc] == 0);
	}


}
