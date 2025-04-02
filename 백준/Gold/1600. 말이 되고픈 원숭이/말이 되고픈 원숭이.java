import java.io.*;
import java.util.*;

public class Main {
	static int K, C, R, result;
	static int dr[] = {0, 1, -1, 0, -1, -2, 1, -2, 2, -1, 2, 1}; //원숭이(4) + 말(8)의 움직임
	static int dc[] = {1, 0, 0, -1, 2, 1, 2, -1, 1, -2, -1, -2};
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
		if(R == 1 && C == 1) result = 0;
		else result = bfs();

		
		/* 출력 */
		System.out.print(result);
	}

	

	static int bfs() {
		Queue<Monkey> q = new ArrayDeque<>();
		q.offer(new Monkey(0, 0, 0, K));
		visited[K][0][0] = true;
		while(!q.isEmpty()) {
			Monkey cur = q.poll();
			int depth = cur.d;
			for (int i = 0; i < 12; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				int nh = cur.h;
				if(i > 3) { //말이 될 수 있니~?
					if(cur.h == 0) break; //No 못함 (원숭이 이동에서 끝)
					else nh--; //Yes 가능 말로 이동(횟수권 1회 감소)
				}
				if(!check(nr, nc) || board[nr][nc] || visited[nh][nr][nc]) continue;
				if(nr == R-1 && nc == C-1) return depth+1;
				visited[nh][nr][nc] = true;
				q.offer(new Monkey(nr, nc, depth+1, nh));
			}
		}
		return -1;
	}



	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < R) && (0 <= nc && nc < C);
	}

}
