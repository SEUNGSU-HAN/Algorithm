import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, K;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] board;
	static class Car {
		int r, c, k, d, count;

		public Car(int r, int c, int k, int d, int count) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.d = d;
			this.count = count;
		}
	}
	static Car rc;
	static int[][][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			board = new char[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine().trim();
				for (int j = 0; j < N; j++) {
					board[i][j] = line.charAt(j);
					if(board[i][j] == 'X') rc = new Car(i, j, K, 0, 0);
				}
			}
			visited = new int[K+1][N][N];
			
			/* 로직 */
			sb.append("#").append(test_case).append(" ").append(bfs()).append("\n");
			
			/* 출력 */
		}
		
		System.out.print(sb);
	}

	static int bfs() {
		Queue<Car> q = new ArrayDeque<>();
		q.offer(rc);
		visited[rc.k][rc.r][rc.c] = 1;
		int min = 999;
		while(!q.isEmpty()) {
			Car cur = q.poll();
			int k = cur.k;
			int d = cur.d;
			for (int i = 0; i < 4; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				int ncount = cur.count;
				if(!check(nr, nc)) continue;
				if(board[nr][nc] == 'Y') {
					min = Math.min(min, ncount+turn(d, i)+1);
					break;
				}
				if(board[nr][nc] == 'T') {
					if(k > 0) {//나무를 베고 이동할 것 (나무 베는건 카운팅 안댐)
						ncount += turn(d, i)+1;
						if(visited[k-1][nr][nc] != 0 && ncount >= visited[k-1][nr][nc]) continue;
						visited[k-1][nr][nc] = ncount;
						q.offer(new Car(nr, nc, k-1, i, ncount));
					}
				}else {
					ncount += turn(d, i)+1;
					if(visited[k][nr][nc] != 0 && ncount >= visited[k][nr][nc]) continue;
					visited[k][nr][nc] = ncount;
					q.offer(new Car(nr, nc, k, i, ncount));
				}
				//나무 체크
			}
		}
		return min == 999 ? -1 : min;
	}

	static int turn(int d, int i) {
		//방향 전환
		if(d == 0) { //위 방향
			if(i == 1 || i == 3) { //우회전, 좌회전
				return 1;
			}else if(i == 2) { //우회전(혹은 좌회전) 2번
				return 2;
			}
		}else if(d == 1) { //우 방향
			if(i == 0 || i == 2) { //우회전, 좌회전
				return 1;
			}else if(i == 3) { //우회전(혹은 좌회전) 2번
				return 2;
			}
		}else if(d == 2) { //하 방향
			if(i == 1 || i == 3) { //우회전, 좌회전
				return 1;
			}else if(i == 0) { //우회전(혹은 좌회전) 2번
				return 2;
			}
		}else { //좌 방향
			if(i == 0 || i == 2) { //우회전, 좌회전
				return 1;
			}else if(i == 1) { //우회전(혹은 좌회전) 2번
				return 2;
			}
		}
		return 0;
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
