import java.io.*;
import java.util.*;

public class Main {
	static int M, N, H, canApple;
	static int[] dr = {0, 0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 0, 1, 0, -1};
	static int[] dz = {1, -1, 0, 0, 0, 0};
	static int[][][] board;
	static boolean[][][] visited;
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); //열
		N = Integer.parseInt(st.nextToken()); //행
		H = Integer.parseInt(st.nextToken()); //높이
				
		/* 초기화 */
		board = new int[H][N][M];
		visited = new boolean[H][N][M];
		q = new ArrayDeque<>();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					board[i][j][k] = Integer.parseInt(st.nextToken());
					if(board[i][j][k] == 0) canApple++;
					if(board[i][j][k] == 1) {
						visited[i][j][k] = true;
						q.add(new int[] {i, j, k});
					}
				}
			}
		}
		
		
		/* 로직+출력 */
		System.out.println(bfs());
}

	static int bfs() {
		int count = -1;
		while(!q.isEmpty()) {
			int n = q.size();
			count++;
			while(n-- > 0) {
				int[] cur = q.poll();
				int z = cur[0];
				int r = cur[1];
				int c = cur[2];
				for (int i = 0; i < 6; i++) {
					int nz = z+dz[i];
					int nr = r+dr[i];
					int nc = c+dc[i];
					if(check(nz, nr, nc) && !visited[nz][nr][nc]){
						if(board[nz][nr][nc] == -1) continue;
						q.offer(new int[] {nz, nr, nc});
						visited[nz][nr][nc] = true;
						canApple--;
					}
				}
			}
		}
		
		return canApple == 0 ? count : -1;
	}

	static boolean check(int nz, int nr, int nc) {
		return (nz >= 0 && nz < H) && (nr >= 0 && nr < N) && (nc >= 0 && nc < M);
	}

}
