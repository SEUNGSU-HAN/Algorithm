import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] start, goal;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			N = Integer.parseInt(br.readLine().trim());
			
			/* 초기화 */
			start = new int[3];
			goal = new int[2];
			st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			goal[0] = Integer.parseInt(st.nextToken());
			goal[1] = Integer.parseInt(st.nextToken());
			visited = new boolean[N][N];
			
			/* 로직 */
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(start);
			visited[start[0]][start[1]] = true;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				if(N*cur[0]+cur[1] == N*goal[0]+goal[1]) {
					sb.append(cur[2]).append("\n");
					break;
				}
				for (int i = 0; i < 8; i++) {
					int nr = cur[0]+dr[i];
					int nc = cur[1]+dc[i];
					if(check(nr, nc) && !visited[nr][nc]) {
						q.offer(new int[] {nr, nc, cur[2]+1});
						visited[nr][nc] = true;
					}
				}
			}			
		}
		/* 출력 */
		System.out.print(sb);
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
