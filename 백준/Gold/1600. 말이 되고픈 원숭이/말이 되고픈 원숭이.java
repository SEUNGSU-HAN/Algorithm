import java.io.*;
import java.util.*;

public class Main {
	static int K, C, R, result;
	static int dr[] = {0, 1, -1, 0}; //원숭이의 움직임
	static int dc[] = {1, 0, 0, -1};
	static int hdr[] = {-1, -2, 1, -2, 2, -1, 2, 1}; //말의 움직임
	static int hdc[] = {2, 1, 2, -1, 1, -2, -1, -2};
	static class Monkey {
		int r, c, h, d; //r, c, horse권, depth

		public Monkey(int r, int c, int d, int h) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.h = h;
		}
	}
	static boolean[][][] visited;
	static boolean[][] board;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				if(st.nextToken().equals("1")) board[i][j] = true;
			}
		}
		visited = new boolean[K+1][R][C];
		
		/* 로직 */
		result = bfs();
		
		/* 출력 */
		System.out.print(result);
	}

	

	static int bfs() {
		Queue<Monkey> q = new ArrayDeque<>();
		q.offer(new Monkey(0, 0, 0, K));
		visited[K][0][0] = true;
		while(!q.isEmpty()) {
			Monkey cur = q.poll();
			int h = cur.h;
			int depth = cur.d;
			if(cur.r == R-1 && cur.c == C-1) return depth;
			if(cur.h > 0) {
				for (int i = 0; i < 8; i++) {
					int nr = cur.r+hdr[i];
					int nc = cur.c+hdc[i];
					if(!check(nr, nc) || board[nr][nc] || visited[h-1][nr][nc]) continue;
					visited[h-1][nr][nc] = true;
					q.offer(new Monkey(nr, nc, depth+1, h-1));
				}
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				if(!check(nr, nc) || board[nr][nc] || visited[h][nr][nc]) continue;
				visited[h][nr][nc] = true;
				q.offer(new Monkey(nr, nc, depth+1, h));
			}
		}
		return -1;
	}



	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < R) && (0 <= nc && nc < C);
	}

}
