import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//연구소
public class Main {
	static int N, M, W=3;
	static int safe = 0;
	static int[][] board;
	static int[][] testBoard;
	static boolean[][] visited;
	static Coord[] wall; //벽 위치 (index = 행, 열 = 값)
	static ArrayList<Coord> cds; //안전구역 모음
	static ArrayList<Coord> virus;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		visited = new boolean[N][M];
		board = new int[N][M];
		testBoard = new int[N][M];
		cds = new ArrayList<>();
		virus = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				testBoard[i][j] = board[i][j];
				if(board[i][j] == 0) cds.add(new Coord(i,j));
				if(board[i][j] == 2) virus.add(new Coord(i, j));
			}
		}
		wall = new Coord[N];
		
		
		/* 로직 */
		//벽은 3개며 반드시 3개 설치 -> 벽을 설치할 수 있는 위치 3곳에 벽을 설치
		//조합 써보자 -> 0인 곳에서 3개 뽑아서 벽 세우기 (nCr)
		//벽 세우고 바이러스 퍼트려서 남은 0의 개수 찾기 (bfs)
		combi(0, 0);
		
		
		/* 출력 */
		System.out.print(safe);
	}

	private static void combi(int cnt, int start) {
		if(cnt == W) {
			createWall();
			for (int i = 0; i < virus.size(); i++) {
				bfs(i);
			}
			safeCheck();
			boardCopy();
			deleteWall();
			return;
		}
		for (int i = start; i < cds.size(); i++) {
			wall[cnt] = cds.get(i);
			combi(cnt+1, i+1);
			wall[cnt] = null;
		}
	}
	
	private static void safeCheck() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(testBoard[i][j] == 0) cnt++;
			}
		}
		safe = Math.max(safe, cnt);
	}

	private static void bfs(int start) {
		Queue<Coord> q= new ArrayDeque<>();
		q.offer(virus.get(start));
		visited[virus.get(start).r][virus.get(start).c] = true;
		while(!q.isEmpty()) {
			Coord cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(check(nr, nc)) {
					q.offer(new Coord(nr, nc));
					testBoard[nr][nc] = 2;
					visited[nr][nc] = true;
				}
			}
		}
		visited = new boolean[N][M];
	}

	private static boolean check(int nr, int nc) {
		if((0 <= nr && nr < N) && (0 <= nc && nc < M) 
				&& !visited[nr][nc] && testBoard[nr][nc] == 0)
			return true;
		
		return false;
	}

	private static void deleteWall() {
		for (int i = 0; i < W; i++) {
			testBoard[wall[i].r][wall[i].c] = 0;
		}
	}

	private static void createWall() {
		for (int i = 0; i < W; i++) {
			testBoard[wall[i].r][wall[i].c] = 1;
		}
	}

	private static void boardCopy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				testBoard[i][j] = board[i][j];
			}
		}
	}

	static class Coord{
		int r, c;
		
		Coord(int r, int c){
			this.r = r;
			this.c = c;
		}
	}

}
