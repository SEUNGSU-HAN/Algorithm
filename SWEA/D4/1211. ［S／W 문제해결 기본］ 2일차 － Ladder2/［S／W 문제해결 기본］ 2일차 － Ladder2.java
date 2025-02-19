import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int T, N=100;
	static int[][] board;
	static ArrayList<int[]> sp; //start point
	static boolean[][] visited;
	static int minDist;
	static int[] dr = {0, 0, 1}; //우,좌,하
	static int[] dc = {1, -1, 0}; 
	static int direct;
	static ArrayList<Integer> result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 0; t < 10; t++) {
			/* 입력 */
			StringTokenizer st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			board = new int[N][N];
			sp = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(i == 0 && board[i][j] == 1) sp.add(new int[] {i, j});
				}
			}
			minDist = Integer.MAX_VALUE;
			result = new ArrayList<>();
			
			/* 로직 */
			for (int i = 0; i < sp.size(); i++) {
				visited = new boolean[N][N];
				direct = 2;
				dfs(sp.get(i)[0], sp.get(i)[1], 0, direct);
			}
			int x = -1;
			for (int i = sp.size()-1; i >= 0; i--) {
				if(minDist == result.get(i)) x = sp.get(i)[1];
			}
			
			/* 출력 */
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(T).append(" ").append(x).append("\n");
			System.out.print(sb);
		}
	}

	private static void dfs(int r, int c, int tot, int d) {
		if(r == N) {
			result.add(tot);
			minDist = Math.min(minDist, tot);
			return;
		}
		visited[r][c] = true;
		boolean isturn = false; //방향을 전환했는지 여부(했다면 더이상 다른 루트 볼 필요 없음)
		//3방 탐색 (우, 좌, 하 순서)
		//먼저 보이는 곳 바로 직진
		for (int i = 0; i < 3; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(check(nr, nc)) {
				d = i;
				break;
			}
		}
		
		dfs(r+dr[d], c+dc[d], tot+1, d);
	}

	private static boolean check(int nr, int nc) {
		return ((0 <= nr && nr < N) && (0 <= nc && nc < N)) 
				&& board[nr][nc] == 1
				&& !visited[nr][nc];
	}

}
