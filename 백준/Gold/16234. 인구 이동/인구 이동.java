import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//인구이동
public class Main {
	static int N, L, R;
	static int[][] board;
	static int[][] tmpBoard;
	static ArrayList<Integer>[] alliance;
	static boolean[] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int allNum; //연합군 수
	static int[] allInfo; //해당 연합에 분배될 인구 수
	static int result;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		init();

		/* 로직 */
		//연합군 찾기 (연합군 있으면 true, 없으면 false)
		while(findAlliance()) {
			//연합군 분리(여러 연합군이 있을 경우 고려 -> 플러드필 사용)
			for (int i = 0; i < N*N; i++) {
				if(alliance[i].size() != 0 && !visited[i]) {
					allNum++;
					allInfo[allNum] = bfs(i);
				}
			}
			//인구 이동
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(tmpBoard[i][j] != 0) {
						board[i][j] = allInfo[tmpBoard[i][j]];
					}
				}
			}
			result++;
			init();
		}

		
		/* 출력 */
		System.out.print(result);
	}

	private static boolean findAlliance() {
		boolean find = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					int nr = i+dr[k];
					int nc = j+dc[k];
					if(check(nr, nc, board[i][j])) {
						alliance[i*N+j].add(nr*N+nc);
						find = true;
					}
				}
			}
		}
		return find;
	}

	private static void init() {
		alliance = new ArrayList[N*N];
		for (int i = 0; i < N*N; i++) {
			alliance[i] = new ArrayList<>();
		}
		visited = new boolean[N*N];
		tmpBoard = new int[N][N];
		allInfo = new int[N*N];
		allNum = 0;
	}

	private static int bfs(int start) {
		int an = 1; //연합 국가 수
		int totp = 0; //연합 인구 총 수
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		tmpBoard[start/N][start%N] = allNum;
		totp += board[start/N][start%N];
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < alliance[cur].size(); i++) {
				int n = alliance[cur].get(i);
				if(!visited[n]) {
					q.offer(n);
					visited[n] = true;
					an++;
					tmpBoard[n/N][n%N] = allNum;
					totp += board[n/N][n%N];
				}
			}
		}
		return totp/an;
	}

	private static boolean check(int nr, int nc, int p) {
		if((0 <= nr && nr < N) && (0 <= nc && nc < N)) {
			int peopleSub = Math.abs(board[nr][nc] - p);
			if((L <= peopleSub && peopleSub <= R)) return true;
		}
		return false;
	}

}
