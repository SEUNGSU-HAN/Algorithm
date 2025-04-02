import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[] dr = {1, 0, -1, 0}; // 하우상좌
	static int[] dc = {0, 1, 0, -1};
	static int[] start;
	static int[][] board;
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] cl = br.readLine().toCharArray();
			for (int j = 0; j < cl.length; j++) {
				board[i][j] = cl[j]-'0';
			}
		}
		start = new int[] {K, 0, 0, 1}; //세계선, r, c, depth
		visited = new boolean[K+1][N][M];
		
		/* 로직 */
		//3차원 배열을 활용해 세계선 관리
		//벽을 부순 세계선, 안 부순 세계선
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[K][start[1]][start[2]] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int z = cur[0];
			int depth = cur[3];
			if(cur[1] == N-1 && cur[2] == M-1) {
				System.out.print(depth);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[1]+dr[i];
				int nc = cur[2]+dc[i];
				if(!check(nr, nc))continue;
				if(board[nr][nc] == 1) {
					if(z > 0) {
						if(visited[z-1][nr][nc]) continue;
						visited[z-1][nr][nc] = true;
						q.offer(new int[] {z-1, nr, nc, depth+1});
					}
				}else {
					if(visited[z][nr][nc]) continue;
					visited[z][nr][nc] = true;
					q.offer(new int[] {z, nr, nc, depth+1});
				}
			}
		}

		
		/* 출력 */
		System.out.print(-1);
	}

	static void print(int z) {
		System.out.printf("=======%d 세계관=======\n", z);
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < M; k++) {
				System.out.print(visited[z][j][k] + " ");
			}
			System.out.println();
		}
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < M);
	}

}
