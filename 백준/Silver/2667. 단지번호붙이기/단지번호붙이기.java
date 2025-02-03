import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//FF -> Flood Fill 알고리즘
public class Main {
	static int N;
	static int[][] board;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static ArrayList<Integer> result;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][N];
		result = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				board[i][j] = c[j]-'0';
			}
		}
		
		/* 로직 */
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j] == 1) {
					cnt++;
					bfs(i, j, cnt+1);
				}
			}
		}
		
		/* 출력 */
		System.out.println(cnt);
		Collections.sort(result);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}


	private static void bfs(int cr, int cc, int g) {
		int cnt = 1;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {cr, cc});
		board[cr][cc] = g;
		
		while(!q.isEmpty()) {
			int[] coord = q.poll();
			int r = coord[0];
			int c = coord[1];
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(!check(nr, nc)) continue;
				if(board[nr][nc] == 1) {
					q.offer(new int[]  {nr, nc});
					board[nr][nc] = g;
					cnt++;
				}
			}
		}
		result.add(cnt);
	}

	private static boolean check(int nr, int nc) {
		if((0 <= nr && nr < N) && (0 <= nc && nc < N)) return true;
		return false;
	}

}
