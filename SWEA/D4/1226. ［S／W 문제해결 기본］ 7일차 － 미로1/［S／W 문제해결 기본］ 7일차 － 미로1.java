import java.io.*;
import java.util.*;

public class Solution {
	static int T, N=16;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] start;
	static int[][] board;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			/* 입력 */
			T = Integer.parseInt(br.readLine().trim());
			
			/* 초기화 */
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine().trim();
				for (int j = 0; j < N; j++) {
					board[i][j] = line.charAt(j)-'0';
					if(board[i][j] == 2) start = new int[] {i, j};
				}
			}
			visited = new boolean[N][N];
			
			/* 로직 */
			sb.append("#").append(T).append(" ").append(bfs()).append("\n");
		}
		/* 출력 */
		System.out.print(sb);
	}

	static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc) && !visited[nr][nc] && board[nr][nc] != 1){
					if(board[nr][nc] == 3) return 1;
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
		
		return 0;
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
