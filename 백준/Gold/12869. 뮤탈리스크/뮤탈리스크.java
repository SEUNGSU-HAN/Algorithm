import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dz = {-9, -9, -3, -3, -1, -1};
	static int[] dr = {-3, -1, -9, -1, -9, -3};
	static int[] dc = {-1, -3, -1, -9, -3, -9};
	static int[][][] scv;
	static boolean[][][] visited;
	static Queue<int[]> q;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		int[] start = new int[3];
		for (int i = 0; i < N; i++) {
			start[i] = Integer.parseInt(st.nextToken());
		}
		q.offer(start);
		scv = new int[61][61][61];
		visited = new boolean[61][61][61];
		
		/* 로직 */
		int count = -1;
		loop:
		while(!q.isEmpty()) {
			int n = q.size();
			count++;
			while(n-- > 0) {
				int[] cur = q.poll();
				if(cur[0] == 0 && cur[1] == 0 && cur[2] == 0) break loop;
				for (int i = 0; i < 6; i++) {
					int nz = cur[0]+dz[i] < 0 ? 0 : cur[0]+dz[i];
					int nr = cur[1]+dr[i] < 0 ? 0 : cur[1]+dr[i];
					int nc = cur[2]+dc[i] < 0 ? 0 : cur[2]+dc[i];
					if(!visited[nz][nr][nc]) {
						q.offer(new int[] {nz, nr, nc});
						visited[nz][nr][nc] = true;
					}
				}
			}
		}
		
		/* 출력 */
		System.out.print(count);
	}

}
