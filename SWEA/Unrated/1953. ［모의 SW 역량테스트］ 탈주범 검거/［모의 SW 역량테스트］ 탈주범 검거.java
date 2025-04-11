import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, M, R, C, L;
	static int[][] board;
	static boolean[][] visited;
	static class Curprit {
		int r, c, t;

		public Curprit(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
	static int[][] type1 = {
			{-1, 1, 0, 0},
			{0, 0, -1, 1}
	};
	static int[][] type2 = {
			{-1, 1},
			{0, 0}
	};
	static int[][] type3 = {
			{0, 0},
			{-1, 1}
	};
	static int[][] type4 = {
			{-1, 0},
			{0, 1}
	};
	static int[][] type5 = {
			{1, 0},
			{0, 1}
	};
	static int[][] type6 = {
			{1, 0},
			{0, -1}
	};
	static int[][] type7 = {
			{-1, 0},
			{0, -1}
	};
	static Curprit curprit;
	static ArrayList<int[][]> tunnel;
	static ArrayList<Integer>[] canGo;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			board = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			curprit = new Curprit(R, C, board[R][C]);
			visited = new boolean[N][M];
			tunnel = new ArrayList<>();
			tunnel.add(new int[][] {});
			tunnel.add(type1);
			tunnel.add(type2);
			tunnel.add(type3);
			tunnel.add(type4);
			tunnel.add(type5);
			tunnel.add(type6);
			tunnel.add(type7);
			canGo = new ArrayList[4];
			for (int i = 0; i < 4; i++) {
				canGo[i] = new ArrayList<>();
			}
			//상
			canGo[0].add(1);
			canGo[0].add(2);
			canGo[0].add(5);
			canGo[0].add(6);
			//하
			canGo[1].add(1);
			canGo[1].add(2);
			canGo[1].add(4);
			canGo[1].add(7);
			//좌
			canGo[2].add(1);
			canGo[2].add(3);
			canGo[2].add(4);
			canGo[2].add(5);
			//우
			canGo[3].add(1);
			canGo[3].add(3);
			canGo[3].add(6);
			canGo[3].add(7);
			
			/* 로직 */
			sb.append("#").append(test_case).append(" ").append(bfs()).append("\n");
			
			
		}
		/* 출력 */
		System.out.print(sb);
	}
	/*
	1
	상 : 2, 5, 6
	하 : 2, 4, 7
	좌 : 3, 4, 5
	우 : 3, 6, 7
	
	2
	상 : 1, 5, 6
	하 : 1, 4, 7
	
	3
	좌 : 1, 4, 5
	우 : 1, 6, 7
	
	4
	상 : 1, 2, 5, 6
	우 : 1, 3, 6, 7
	
	5
	하 : 1, 2, 4, 7
	우 : 1, 3, 6, 7
	
	6
	하 : 1, 2, 4, 7
	좌 : 1, 3, 4, 5
	
	7
	상 : 1, 2, 5, 6
	좌 : 1, 3, 4, 5
	
	상 : 1, 2, 5, 6
	하 : 1, 2, 4, 7
	좌 : 1, 3, 4, 5
	우 : 1, 3, 6, 7
	*/

	static int bfs() {
		Queue<Curprit> q = new ArrayDeque<>();
		q.offer(curprit);
		visited[curprit.r][curprit.c] = true;
		int count = 1;
		while(!q.isEmpty()) {
			int n = q.size();
			if(L-- == 0) break;
			while (n-- > 0 && L > 0) {
				Curprit cur = q.poll();
				int[][] t = tunnel.get(cur.t);
				for (int i = 0; i < t[0].length; i++) {
					int nr = cur.r+t[0][i];
					int nc = cur.c+t[1][i];
					boolean isgo = false;
					if(!check(nr, nc) || visited[nr][nc] || board[nr][nc] == 0) continue;
					//상 방향
					if(t[0][i] == -1 && t[1][i] == 0) {
						isgo = canGo[0].contains(board[nr][nc]) ? true : false;
					}
					//하 방향
					else if(t[0][i] == 1 && t[1][i] == 0) {
						isgo = canGo[1].contains(board[nr][nc]) ? true : false;
					}
					//좌 방향
					else if(t[0][i] == 0 && t[1][i] == -1) {
						isgo = canGo[2].contains(board[nr][nc]) ? true : false;
					}
					//우 방향
					else if(t[0][i] == 0 && t[1][i] == 1) {
						isgo = canGo[3].contains(board[nr][nc]) ? true : false;
					}
					if(isgo) {
						visited[nr][nc] = true;
						q.offer(new Curprit(nr, nc, board[nr][nc]));
						count++;
					}
				}
			}
		}
		return count;
	}
	
	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < M);
	}

}
